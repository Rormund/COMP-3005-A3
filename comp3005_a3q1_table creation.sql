-- Table: public.students

-- DROP TABLE IF EXISTS public.students;

CREATE TABLE IF NOT EXISTS public.students
(
    student_id integer NOT NULL DEFAULT nextval('students_student_id_seq'::regclass),
    first_name text COLLATE pg_catalog."default" NOT NULL,
    last_name text COLLATE pg_catalog."default" NOT NULL,
    email text COLLATE pg_catalog."default" NOT NULL,
    enrollment_date date,
    CONSTRAINT students_pkey PRIMARY KEY (student_id),
    CONSTRAINT students_email_key UNIQUE (email)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.students
    OWNER to postgres;