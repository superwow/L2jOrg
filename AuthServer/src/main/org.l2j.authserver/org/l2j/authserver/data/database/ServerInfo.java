package org.l2j.authserver.data.database;

import org.l2j.commons.database.annotation.Column;

public class ServerInfo {

    @Column("server_id")
    private int serverId;
    private String host;
    @Column("server_type")
    private int serverType;

    public ServerInfo() { }

    public int getId() {
        return serverId;
    }
}
