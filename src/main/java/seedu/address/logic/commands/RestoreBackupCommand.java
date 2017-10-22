package seedu.address.logic.commands;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.storage.RestoreBackupDataEvent;
import seedu.address.commons.events.ui.RequestingUserPermissionEvent;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ReadOnlyAddressBook;

/**
 * Replace the current address book with data from backup address book.
 */
public class RestoreBackupCommand extends PermissionCommand {
    public static final String COMMAND_WORD = "restore";
    public static final String COMMAND_ALIAS = "rb";
    public static final String MESSAGE_SUCCESS = "Data has been restored";
    public static final String MESSAGE_FAILURE = "Data has not been restored";
    public static final String MESSAGE_WARNING =
            "Restoring backup will result in the lost of current data. Do you still want to proceed? y/n";

    @Override
    public CommandResult execute() throws CommandException {
        EventsCenter.getInstance().post(new RequestingUserPermissionEvent());
        return new CommandResult(String.format(MESSAGE_WARNING));
    }

    @Override
    public CommandResult executeAfterUserPermission(boolean userPermission) throws CommandException {
        if (userPermission) {
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
