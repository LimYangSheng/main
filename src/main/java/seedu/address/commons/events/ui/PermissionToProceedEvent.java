package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;

/**
 * Indicates a request for Address Book to get users permission to proceed with a command
 */
public class PermissionToProceedEvent extends BaseEvent {
    private String permissionRequestDisplayText;
    private boolean permission;

    public PermissionToProceedEvent (String permissionRequestDisplayText) {
        this.permissionRequestDisplayText = permissionRequestDisplayText;
    }

    public String getPermissionRequestDisplayText() {
        return getPermissionRequestDisplayText();
    }

    public void setPermission(boolean permission) {
        this.permission = permission;
    }

    public boolean getPermissionStatus() {
        return permission;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
