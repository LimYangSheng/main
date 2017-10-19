package seedu.address.logic.commands;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.storage.RestoreBackupDataEvent;
import seedu.address.commons.events.ui.PermissionToProceedEvent;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ReadOnlyAddressBook;

/**
 * Replace the current address book with data from backup address book.
 */
public class RestoreBackupCommand extends Command {
    public static final String COMMAND_WORD = "restore";
    public static final String COMMAND_ALIAS = "rb";
    public static final String MESSAGE_SUCCESS = "Data has been restored";
    public static final String MESSAGE_FAILURE = "Data has not been restored";
    public static final String MESSAGE_WARNING =
            "Restore backup will result in the lost of current data. Do you still want to proceed?";

    @Override
    public CommandResult execute() throws CommandException {
        PermissionToProceedEvent permissionEvent = new PermissionToProceedEvent(MESSAGE_WARNING);
        EventsCenter.getInstance().post(permissionEvent);
        boolean permission = permissionEvent.getPermissionStatus();

        if (permission) {
            RestoreBackupDataEvent event = new RestoreBackupDataEvent();
            EventsCenter.getInstance().post(event);
            ReadOnlyAddressBook backupAddressBookData = event.getAddressBookData();
            model.resetData(backupAddressBookData);
            return new CommandResult(String.format(MESSAGE_SUCCESS));
        }
        else {
            return new CommandResult(String.format(MESSAGE_FAILURE));
        }
    }
}
