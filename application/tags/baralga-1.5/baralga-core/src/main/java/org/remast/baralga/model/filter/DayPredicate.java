package org.remast.baralga.model.filter;

import org.apache.commons.collections.Predicate;
import org.joda.time.DateTime;
import org.remast.baralga.model.ProjectActivity;

/**
 * Holds for all project activities of one day.
 * @author remast
 */
public class DayPredicate implements Predicate {

    /**
     * The day to check for.
     */
    private final DateTime dateInDay;

    /**
     * Creates a new predicate that holds for the given day.
     * 
     * @param dateInDay
     *            the day the predicate holds for
     */
    public DayPredicate(final DateTime dateInDay) {
        this.dateInDay = dateInDay;
    }

    /**
     * Checks if this predicate holds for the given object.
     * 
     * @param object
     *            the object to check
     * @return <code>true</code> if the given object is a project activity of
     *         that day else <code>false</code>
     */
    public boolean evaluate(final Object object) {
        if (object == null) {
            return false;
        }

        if (!(object instanceof ProjectActivity)) {
            return false;
        }

        final ProjectActivity activity = (ProjectActivity) object;
        return this.dateInDay.getDayOfWeek() == activity.getStart().getDayOfWeek();
    }

}