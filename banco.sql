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
    status integer,
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

INSERT INTO fases_processo (id, descricao) VALUES (1, 'informativa'),(2, 'deliberativa'),(3, 'aceito'),(4, 'negado'),(5, 'intimacao'),(6, 'encerramento');

INSERT INTO processo_fase (id, processo, responsavel, fase, data) VALUES (150, 100, 3, 1, '2020-05-15');


INSERT INTO processos (id, juiz, advogadopromovente, promovente, promovido, advogadopromovido, status, data)
VALUES(100, 1, 3, 2, 5, 4, 1, '2020-05-15');

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

