module DatabaseModule {
    exports priv.stud.database.services;
    exports priv.stud.database.entities;
    exports priv.stud.database.entities.warehouse;
    exports priv.stud.database.entities.stores;
    exports priv.stud.database.entities.ropes;
    exports priv.stud.database.entities.orders;
    requires org.hibernate.orm.core;
    requires java.validation;
    requires sqlite.dialect;
    requires org.xerial.sqlitejdbc;
    requires static lombok;
    requires java.persistence;

    opens priv.stud.database.entities to org.hibernate.orm.core;
    opens priv.stud.database.entities.ropes to org.hibernate.orm.core;
    opens priv.stud.database.entities.orders to org.hibernate.orm.core;
    opens priv.stud.database.entities.warehouse to org.hibernate.orm.core;
    opens priv.stud.database.entities.stores to org.hibernate.orm.core;

}