package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

//@@author alexanderleegs
/**
 * Represents a Meeting in the address book.
 * Guarantees: immutable
 */
public class Meeting {

    public static final String MESSAGE_TIME_CONSTRAINTS = "Time format should be YYYY-MM-DD HH-MM";

    public final LocalDateTime date;
    public final String value;
    private Person person;
    private ObjectProperty<Name> displayName;

    /**
     * Validates given tag name.
     *
     * @throws IllegalValueException if the given tag name string is invalid.
     */
    public Meeting(String time, Person person) throws IllegalValueException {
        setPerson(person);
        requireNonNull(time);
        String trimmedTime = time.trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            LocalDateTime date = LocalDateTime.parse(trimmedTime, formatter);
            this.date = date;
            value = date.format(formatter);
        } catch (DateTimeParseException dtpe) {
            throw new IllegalValueException(MESSAGE_TIME_CONSTRAINTS);
        }
    }

    /**
     * Overloaded constructor to be used in edit command parser
     */
    public Meeting(String time) throws IllegalValueException {
        requireNonNull(time);
        String trimmedTime = time.trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            LocalDateTime date = LocalDateTime.parse(trimmedTime, formatter);
            this.date = date;
            value = date.format(formatter);
        } catch (DateTimeParseException dtpe) {
            throw new IllegalValueException(MESSAGE_TIME_CONSTRAINTS);
        }
    }

    /**
     * Set the person attributes of the meeting object.
     */
    public void setPerson(Person person) {
        this.person = person;
        this.displayName = new SimpleObjectProperty<>(person.getName());
    }

    /**
     * Returns person of the meeting
     */
    public Person getPerson() {
        return person;
    }

    /**
     * Return name for use by UI
     */
    public ObjectProperty<Name> nameProperty() {
        return displayName;
    }


    @Override
    public boolean equals(Object other) {
        /* Only happens for testing as name attribute will be set for the main app*/
        if (this.person == null && other instanceof Meeting && ((Meeting) other).person == null) {
            return other == this // short circuit if same object
                    || (other instanceof Meeting // instanceof handles nulls
                    && this.date.equals(((Meeting) other).date)); //state check
        }

        return other == this // short circuit if same object
                || (other instanceof Meeting // instanceof handles nulls
                && this.date.equals(((Meeting) other).date)
                && this.person.equals(((Meeting) other).person)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return "[" + value + "]";
    }

}
