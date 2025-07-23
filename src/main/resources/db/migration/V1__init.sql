CREATE TABLE IF NOT EXISTS public.elearning_categories
(
    id smallint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 32767 CACHE 1 ),
    name text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT elearnings_category_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE IF NOT EXISTS users_id_seq START 1;
CREATE SEQUENCE IF NOT EXISTS elearning_users_id_seq START 1;
CREATE SEQUENCE IF NOT EXISTS metatags_id_seq START 1;
CREATE SEQUENCE IF NOT EXISTS elearning_metatags_id_seq START 1;

CREATE TABLE IF NOT EXISTS public.elearings
(
    id text COLLATE pg_catalog."default" NOT NULL,
    name text COLLATE pg_catalog."default" NOT NULL,
    "imageUrl" text COLLATE pg_catalog."default",
    description text COLLATE pg_catalog."default",
    duration real,
    type text COLLATE pg_catalog."default" NOT NULL,
    category_id smallint NOT NULL,
    "startDate" timestamp with time zone,
    "endDate" timestamp with time zone,
    CONSTRAINT elearings_pkey PRIMARY KEY (id),
    CONSTRAINT elearning_categories_fk FOREIGN KEY (category_id)
        REFERENCES public.elearning_categories (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);
CREATE TABLE IF NOT EXISTS public.users
(
    id bigint NOT NULL DEFAULT nextval('users_id_seq'::regclass),
    username text COLLATE pg_catalog."default" NOT NULL,
    password text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS public.elearning_users
(
    id bigint NOT NULL DEFAULT nextval('elearning_users_id_seq'::regclass),
    elearning_id text COLLATE pg_catalog."default" NOT NULL,
    user_id bigint NOT NULL,
    "startDate" timestamp with time zone,
    "endDate" timestamp with time zone,
    status text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT elearning_users_pkey PRIMARY KEY (id),
    CONSTRAINT fk_elearning FOREIGN KEY (elearning_id)
        REFERENCES public.elearings (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_user FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

CREATE TABLE IF NOT EXISTS public.metatags
(
    id integer NOT NULL DEFAULT nextval('metatags_id_seq'::regclass),
    name text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT metatags_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.elearning_metatags
(
    id integer NOT NULL DEFAULT nextval('elearning_metatags_id_seq'::regclass),
    elearning_id text COLLATE pg_catalog."default" NOT NULL,
    metatag_id integer NOT NULL,
    CONSTRAINT elearning_metatags_pkey PRIMARY KEY (id),
    CONSTRAINT elearning_fk FOREIGN KEY (elearning_id)
        REFERENCES public.elearings (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT meatag_fk FOREIGN KEY (metatag_id)
        REFERENCES public.metatags (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);