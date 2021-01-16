module modules.db {
    requires java.sql;
    requires sqlite.jdbc;
    requires transitive modules.common;
    exports modules.db;
}