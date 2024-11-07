SET check_function_bodies = false
;

CREATE TABLE dimension(
                          id serial NOT NULL,
                          width double precision NOT NULL,
                          height double precision NOT NULL,
                          depth double precision NOT NULL,
                          CONSTRAINT dimension_pkey PRIMARY KEY(id)
);

CREATE TABLE foam(
                     id serial NOT NULL,
                     dimension_id integer NOT NULL,
                     parent_id integer NOT NULL,
                     cost_price double precision NOT NULL,
                     residue double precision NOT NULL DEFAULT 0,
                     creation_date timestamp NOT NULL DEFAULT NOW(),
                     is_last boolean NOT NULL DEFAULT true,
                     CONSTRAINT foam_pkey PRIMARY KEY(id),
                     CONSTRAINT mattress_block_key UNIQUE(parent_id)
);

CREATE TABLE stock(
                      id serial NOT NULL,
                      quantity integer NOT NULL,
                      standard_size_id integer NOT NULL,
                      CONSTRAINT stock_pkey PRIMARY KEY(id)
);

CREATE TABLE production(
                           id serial NOT NULL,
                           foam_id integer NOT NULL,
                           size_type_id integer NOT NULL,
                           quantity integer NOT NULL,
                           cost_price double precision NOT NULL,
                           production_date date NOT NULL,
                           CONSTRAINT production_pkey PRIMARY KEY(id)
);

CREATE TABLE size_type(
                          id serial NOT NULL,
                          "name" varchar(50) NOT NULL,
                          selling_price double precision NOT NULL,
                          block_id integer NOT NULL,
                          CONSTRAINT size_type_pkey PRIMARY KEY(id),
                          CONSTRAINT standard_size_key UNIQUE(selling_price)
);

ALTER TABLE foam
    ADD CONSTRAINT foam_dimension_id_fkey
        FOREIGN KEY (dimension_id) REFERENCES dimension (id);

ALTER TABLE size_type
    ADD CONSTRAINT size_type_block_id_fkey
        FOREIGN KEY (block_id) REFERENCES dimension (id);

ALTER TABLE foam
    ADD CONSTRAINT source_id FOREIGN KEY (parent_id) REFERENCES foam (id);

ALTER TABLE production
    ADD CONSTRAINT production_foam_id_fkey
        FOREIGN KEY (foam_id) REFERENCES foam (parent_id);

ALTER TABLE production
    ADD CONSTRAINT production_size_type_id_fkey
        FOREIGN KEY (size_type_id) REFERENCES size_type (id);

ALTER TABLE stock
    ADD CONSTRAINT stock_standard_size_id_fkey
        FOREIGN KEY (standard_size_id) REFERENCES size_type (id);
