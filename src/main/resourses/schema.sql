drop table if exists public.items;

CREATE TABLE IF NOT EXISTS public.items (
                                     id VARCHAR  NOT NULL,
                                     date VARCHAR(255)  NOT NULL,
                                     parent_id VARCHAR,
                                     url VARCHAR(255),
                                     type VARCHAR(255) NOT NULL,
                                     size INT8,
                                     CONSTRAINT pk_item PRIMARY KEY (id)

);

