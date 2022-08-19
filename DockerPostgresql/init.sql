drop table if exists T_ROLES;
create table T_ROLES (
	"rol_id" serial not null,
	"rol_nombre" varchar(100) not null,
	"rol_descripcion" varchar(150) not null,
	primary key ("rol_id")
);

drop table if exists T_USUARIOS;
create table T_USUARIOS (
	"usr_id" serial not null,
	"usr_username" varchar(150) not null,
	"usr_password" varchar(255) not null,
	"usr_rol_id" int not null,
	primary key ("usr_id"),
	constraint "fk_usuario_rol"
		foreign key ("usr_rol_id")
		references T_ROLES ("rol_id")
		on delete no action
		on update no action
);

drop table if exists T_EMPLEADOS;
create table T_EMPLEADOS(
	"emp_id" serial not null,
	"emp_cedula" varchar(10) not null,
	"emp_nombres" varchar(150) not null,
	"emp_apellidos" varchar(150) not null,
	"emp_email" varchar(100) not null,
	"emp_fecha_nac" timestamp null,
	"emp_telefono" varchar(10) null,
	"emp_direccion" varchar(255) null,
	"emp_estado_vacuna" boolean null,
	"emp_usr_id" int not null,
	primary key ("emp_id"),
	unique("emp_cedula"),
    unique("emp_email"),
	constraint "fk_empleado_usuario"
		foreign key ("emp_usr_id")
		references T_USUARIOS ("usr_id")
		on delete no action
		on update no action
);

drop table if exists T_DOSIS;
create table T_DOSIS(
	"dos_id" serial not null,
	"dos_numero" int not null,
	"dos_vacuna" varchar not null,
	"dos_fecha" timestamp not null,
	"dos_emp_id" int not null,
	primary key ("dos_id"),
	constraint "fk_dosis_empleado"
		foreign key ("dos_emp_id")
		references T_EMPLEADOS ("emp_id")
		on delete no action
		on update no action
);

INSERT INTO t_roles (rol_nombre, rol_descripcion) VALUES('admin', 'Rol Administrador');
INSERT INTO t_roles (rol_nombre, rol_descripcion) VALUES('user', 'Rol Usuario/Empleado');