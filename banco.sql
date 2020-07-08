Cidadaes e estados:

https://gist.github.com/manfe/3975938

CREATE TABLE fases_processo (
    id serial PRIMARY KEY NOT NULL,
    descricao varchar(100) NOT NULL
);

CREATE TABLE processo_fase (
    id serial PRIMARY KEY NOT NULL,
    processo integer NOT NULL,
    responsavel integer NOT NULL,
    fase integer NOT NULL,
    data date NOT NULL,
    arquivo varchar(100),
    resposta varchar(250),
    oficial integer
);

CREATE TABLE processos (
    id serial PRIMARY KEY NOT NULL,
    juiz integer,
    advogadopromovente integer NOT NULL,
    promovente integer NOT NULL,
    promovido integer NOT NULL,
    advogadopromovido integer,
    status int not null DEFAULT 1,
    data date NOT NULL
);

CREATE TABLE tipos_usuarios (
    id serial PRIMARY KEY NOT NULL,
    descricao varchar(50) UNIQUE
);

CREATE TABLE public.usuarios (
    id serial PRIMARY KEY NOT NULL,
    nome varchar(100) NOT NULL,
    email varchar(100) UNIQUE NOT NULL,
    senha character(32) NOT NULL,
    tipo integer NOT NULL
);

INSERT INTO fases_processo (id, descricao) VALUES (1, 'Informativa'),(2, 'Deliberativa'),(3, 'Aceito'),(4, 'Negado'),(5, 'Intimacao'),(6, 'Encerramento');

INSERT INTO processo_fase (id, processo, responsavel, fase, data) VALUES (150, 100, 3, 1, '2020-06-03 00:00:00'),
(950, 100, 1, 4, "2020-06-09 15:55:38.005","nai"),(151, 100, 3, 2, "2020-06-04 00:00:00"),
(152, 101, 4, 1, "2020-06-15 16:00:00"),(153, 102, 6, 1, "2020-05-28 14:55:43"),
(154, 103, 4, 1, "2020-06-17 11:55:00"),(155, 102, 1, 6, "2020-05-29 12:12:22"),
(156, 101, 4, 2, "2020-06-18 00:05:00");

INSERT INTO processo_fase (id, processo, responsavel, fase, data,resposta) VALUES (950, 100, 1, 4, "2020-06-09 15:55:38.005","nai");
INSERT INTO processos (id, juiz, advogadopromovente, promovente, promovido, advogadopromovido, status, data)
VALUES(100, 1, 3, 2, 5, 4, '2020-06-03'),(101, 1, 4, 5, 2, 3, '2020-06-15'),(102, 1, 6, 8, 7, 4, '2020-05-28'),
(103, 9, 4, 7, 8, 6, '2020-06-17');

INSERT INTO tipos_usuarios (id, descricao) VALUES (1,'Juiz'),(2, 'Advogado'), (3, 'Parte');

INSERT INTO usuarios (id, nome, email, senha, tipo) VALUES
(2, 'Maria Joaquina', 'maria@gmail.com', '202cb962ac59075b964b07152d234b70', 3),
(3, 'Leonardo Barbosa', 'leonardo@gmail.com', '202cb962ac59075b964b07152d234b70', 2),
(4, 'Fernando Hoflinger', 'fernando@gmail.com', '202cb962ac59075b964b07152d234b70', 2),
(5, 'Joana Cardoso', 'joana@gmail.com', '202cb962ac59075b964b07152d234b70', 3),
(6, 'Sergio Moro', 'sergio@gmail.com', '202cb962ac59075b964b07152d234b70', 2),
(7, 'Luiz Inacio', 'luiz@gmail.com', '202cb962ac59075b964b07152d234b70', 3),
(8, 'Marcelo Odebretch', 'marcelo@gmail.com', '202cb962ac59075b964b07152d234b70', 3),
(1,	'Hiago Petris', 'hiago@gmail.com', '202cb962ac59075b964b07152d234b70', 1);



--
-- TOC entry 2738 (class 2606 OID 18167)
-- Name: processo_fase processo_fase_fase_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.processo_fase
    ADD CONSTRAINT processo_fase_fase_fkey FOREIGN KEY (fase) REFERENCES public.fases_processo(id);


--
-- TOC entry 2736 (class 2606 OID 18157)
-- Name: processo_fase processo_fase_processo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.processo_fase
    ADD CONSTRAINT processo_fase_processo_fkey FOREIGN KEY (processo) REFERENCES public.processos(id);


--
-- TOC entry 2737 (class 2606 OID 18162)
-- Name: processo_fase processo_fase_responsavel_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.processo_fase
    ADD CONSTRAINT processo_fase_responsavel_fkey FOREIGN KEY (responsavel) REFERENCES public.usuarios(id);


--
-- TOC entry 2732 (class 2606 OID 18121)
-- Name: processos processos_advogadopromovente_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.processos
    ADD CONSTRAINT processos_advogadopromovente_fkey FOREIGN KEY (advogadopromovente) REFERENCES public.usuarios(id);


--
-- TOC entry 2735 (class 2606 OID 18136)
-- Name: processos processos_advogadopromovido_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.processos
    ADD CONSTRAINT processos_advogadopromovido_fkey FOREIGN KEY (advogadopromovido) REFERENCES public.usuarios(id);


--
-- TOC entry 2731 (class 2606 OID 18116)
-- Name: processos processos_juiz_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.processos
    ADD CONSTRAINT processos_juiz_fkey FOREIGN KEY (juiz) REFERENCES public.usuarios(id);


--
-- TOC entry 2733 (class 2606 OID 18126)
-- Name: processos processos_promovente_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.processos
    ADD CONSTRAINT processos_promovente_fkey FOREIGN KEY (promovente) REFERENCES public.usuarios(id);


--
-- TOC entry 2734 (class 2606 OID 18131)
-- Name: processos processos_promovido_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.processos
    ADD CONSTRAINT processos_promovido_fkey FOREIGN KEY (promovido) REFERENCES public.usuarios(id);


--
-- TOC entry 2730 (class 2606 OID 18006)
-- Name: usuarios usuarios_tipo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT usuarios_tipo_fkey FOREIGN KEY (tipo) REFERENCES public.tipos_usuarios(id);


-- Completed on 2020-06-03 16:13:22

--
-- PostgreSQL database dump complete
--

alter table usuarios add column endereco varchar(200);
alter table usuarios add column cidade integer;
alter table usuarios add constraint fk_usuarios_cidade foreign key (cidade) references cidades(id);
update usuarios set cidade = 1;
alter table usuarios add column cpf varchar(11);