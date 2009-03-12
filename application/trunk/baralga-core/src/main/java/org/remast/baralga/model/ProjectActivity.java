package org.remast.baralga.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.joda.time.MutableDateTime;
import org.remast.baralga.FormatUtils;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import edu.umd.cs.findbugs.annotations.SuppressWarnings;

/**
 * An activity for a project.
 * 
 * Invariants of this class (not enforced, yet):
 * - start time must not be after end time
 * - start and end date of an activity must always be on the same day
 * unless end date is at 0:00h. In that situation end date is on the following date to the start date.
 * 
 * @author remast
 */
@XStreamAlias("projectActivity")//$NON-NLS-1$
@SuppressWarnings(value={"EI_EXPOSE_REP","EI_EXPOSE_REP2"},
        justification="We trust callers that they won't change Dates we receive or hand out")
public class ProjectActivity implements Serializable, Comparable<ProjectActivity> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /** Start date of this activity. */
    private Date start;

    /** End date of this activity. */
    private Date end;

    /** The project associated with this activity. */
    private Project project;

    /** The description of this activity. */
    private String description;

    public static final String PROPERTY_START = "org.remast.baralga.model.ProjectActivity.start";

    public static final String PROPERTY_END = "org.remast.baralga.model.ProjectActivity.end";

    /** Artificial property if the day in year of the activity changes. */
    public static final String PROPERTY_DATE = "org.remast.baralga.model.ProjectActivity.date";

    public static final String PROPERTY_PROJECT = "org.remast.baralga.model.ProjectActivity.project";

    public static final String PROPERTY_DESCRIPTION = "org.remast.baralga.model.ProjectActivity.description";

    public ProjectActivity(final Date start, final Date end, final Project project) {
//        if(start.getTime() > end.getTime()) {
//            throw new IllegalArgumentException("Activity end time must not be before start time!");
//        }
        this.start = start;
        this.end = end;
        this.project = project;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * @return the end
     */
    public Date getEnd() {
        return end;
    }

    /**
     * @param end
     *            the end to set
     */
    public void setEnd(final Date end) {
        this.end = end;
    }
    
    /**
     * Sets the end hours and minutes while respecting the class invariants.
     * 
     * Note: When setting the end date to 0:00h it is always supposed to mean
     * midnight i.e. 0:00h the next day!
     */
    @java.lang.SuppressWarnings("deprecation")
    public void setEndHourMinutes(final int hours, final int minutes) {
        if( hours == this.end.getHours() && minutes == this.end.getMinutes() ) {
            return;
        }
        
        MutableDateTime dt = new MutableDateTime(this.end.getTime());
        if(dt.getHourOfDay() == 0 && dt.getMinuteOfHour() == 0) { // adjust day if old end was on midnight
            dt.addDays( -1 );
        }
        
        dt.setHourOfDay(hours);
        dt.setMinuteOfHour(minutes);
        
        if(hours == 0 && minutes == 0) {
            dt.addDays(1); // adjust day if new end is on midnight
        }
        
        if(dt.getMillis() < this.start.getTime()) {
            throw new IllegalArgumentException("End time may not be before start time!");
        }
        
        this.end = dt.toDate();
    }

    /**
     * @return the project
     */
    public Project getProject() {
        return project;
    }

    /**
     * @param project
     *            the project to set
     */
    public void setProject(final Project project) {
        this.project = project;
    }

    /**
     * @return the start
     */
    public Date getStart() {
        return start;
    }

    /**
     * @param start
     *            the start to set
     */
    public void setStart(final Date start) {
        this.start = start;
    }

    /**
     * Sets the start hours and minutes while respecting the class invariants.
     */
    @java.lang.SuppressWarnings("deprecation")
    public void setStartHourMinutes(final int hours, final int minutes) {
        if( hours == this.start.getHours() && minutes == this.start.getMinutes() ) {
            return;
        }
        
        Date newStart = new Date(this.start.getTime());
        newStart.setHours(hours);
        newStart.setMinutes(minutes);
        if(newStart.getTime() > this.end.getTime()) {
            throw new IllegalArgumentException("Start time may not be after end time!");
        }
        
        this.start = newStart;
    }
    
    @Override
    public String toString() {
        return FormatUtils.timeFormat.format(this.start) + " "
                + FormatUtils.timeFormat.format(this.start) + " - " + FormatUtils.timeFormat.format(this.end) + " ("
                + FormatUtils.durationFormat.format(this.getDuration()) + "h) " + this.project;
    }

    @Override
    public int compareTo(final ProjectActivity activity) {
        if (activity == null) {
            return 0;
        }

        // Sort by start date but the other way round. That way the latest
        // activity is always on top.
        return this.getStart().compareTo(activity.getStart()) * -1;
    }
    
    @Override
    public boolean equals(final Object that) {
        if (this == that) {
            return true;
        }
        
        if (that == null || !(that instanceof ProjectActivity)) {
            return false;
        }
        
        final ProjectActivity activity = (ProjectActivity) that;
        
        final EqualsBuilder eqBuilder = new EqualsBuilder();
        
        eqBuilder.append(this.getStart(), activity.getStart());
        eqBuilder.append(this.getEnd(), activity.getEnd());
        eqBuilder.append(this.getProject(), activity.getProject());
        
        return eqBuilder.isEquals();
    }
    
    @Override
    public int hashCode() {
        final HashCodeBuilder hashCodeBuilder = new HashCodeBuilder();
        
        hashCodeBuilder.append(this.getStart());
        hashCodeBuilder.append(this.getEnd());
        hashCodeBuilder.append(this.getProject());
        
        return hashCodeBuilder.toHashCode();
    }
    
    /**
     * Calculate the duration of the given activity in decimal hours.
     * @return decimal value of the duration (e.g. for 30 minutes, 0.5 and so on)
     */    
    public final double getDuration() {
        final long timeMilliSec = end.getTime() - start.getTime();
        final long timeMin = timeMilliSec / 1000 / 60;
        final long hours = timeMin / 60;

        final long mins = timeMin % 60;
        final double minsD = Math.round(mins * (1 + 2.0 / 3.0)) / 100.0;

        return hours + minsD;
    }
}
