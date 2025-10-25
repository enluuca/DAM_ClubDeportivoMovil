package com.example.dam_clubdeportivo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "ClubDeportivo.db";
    private static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TABLA CLIENTE
        db.execSQL("CREATE TABLE Cliente (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT," +
                "apellido TEXT," +
                "dni INTEGER UNIQUE," +
                "fechaNacimiento TEXT," +
                "direccion TEXT," +
                "telefono TEXT," +
                "aptoFisico INTEGER," + // 0 = false, 1 = true
                "asociarse INTEGER," +
                "fechaAlta TEXT" +
                ")");

        // TABLA SOCIO
        db.execSQL("CREATE TABLE Socio (" +
                "id INTEGER PRIMARY KEY," +
                "fechaInscripcion TEXT," +
                "fechaVencimientoCuota TEXT," +
                "numeroCarnet INTEGER," +
                "carnetEntregado INTEGER," +
                "fechaBaja TEXT," +
                "FOREIGN KEY(id) REFERENCES Cliente(id)" +
                ")");

        // TABLA NO SOCIO
        db.execSQL("CREATE TABLE NoSocio (" +
                "id INTEGER PRIMARY KEY," +
                "fechaBaja TEXT," +
                "FOREIGN KEY(id) REFERENCES Cliente(id)" +
                ")");

        // TABLA ACTIVIDAD
        db.execSQL("CREATE TABLE Actividad (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT," +
                "costo REAL" +
                ")");

        // TABLA REGISTRO ACTIVIDAD
        db.execSQL("CREATE TABLE RegistroActividad (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idNoSocio INTEGER," +
                "idActividad INTEGER," +
                "fechaPago TEXT," +
                "monto REAL," +
                "medioPago TEXT," +
                "cantidadCuotas INTEGER," +
                "descuento REAL DEFAULT 0," +
                "montoTotal REAL," +
                "comprobante INTEGER," +
                "FOREIGN KEY(idNoSocio) REFERENCES NoSocio(id)," +
                "FOREIGN KEY(idActividad) REFERENCES Actividad(id)" +
                ")");

        // TABLA CUOTA
        db.execSQL("CREATE TABLE Cuota (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idSocio INTEGER," +
                "fechaPago TEXT," +
                "monto REAL," +
                "medioPago TEXT," +
                "cantidadCuotas INTEGER," +
                "descuento REAL DEFAULT 0," +
                "montoTotal REAL," +
                "fechaVencimiento TEXT," +
                "comprobante INTEGER," +
                "FOREIGN KEY(idSocio) REFERENCES Socio(id)" +
                ")");

        // TABLA USUARIOS
        db.execSQL("CREATE TABLE Usuarios (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "usuario TEXT UNIQUE," +
                "clave TEXT," +
                "rol TEXT" +
                ")");

        // INSERTAR ADMINISTRADOR POR DEFECTO
        db.execSQL("INSERT INTO Usuarios (usuario, clave, rol) VALUES ('ADMIN', '1234', 'ADMINISTRADOR')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Si hay cambio de versión, eliminar tablas y recrearlas
        db.execSQL("DROP TABLE IF EXISTS Cuota");
        db.execSQL("DROP TABLE IF EXISTS RegistroActividad");
        db.execSQL("DROP TABLE IF EXISTS Actividad");
        db.execSQL("DROP TABLE IF EXISTS Socio");
        db.execSQL("DROP TABLE IF EXISTS NoSocio");
        db.execSQL("DROP TABLE IF EXISTS Cliente");
        db.execSQL("DROP TABLE IF EXISTS Usuarios");
        onCreate(db);
    }
}
