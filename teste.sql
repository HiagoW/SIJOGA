--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

-- Started on 2020-06-03 16:12:09

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 209 (class 1259 OID 18143)
-- Name: fases_processo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.fases_processo (
    id integer NOT NULL,
    descricao character varying(100) NOT NULL
);


ALTER TABLE public.fases_processo OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 18141)
-- Name: fases_processo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.fases_processo_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.fases_processo_id_seq OWNER TO postgres;

--
-- TOC entry 2880 (class 0 OID 0)
-- Dependencies: 208
-- Name: fases_processo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.fases_processo_id_seq OWNED BY public.fases_processo.id;


--
-- TOC entry 211 (class 1259 OID 18151)
-- Name: processo_fase; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.processo_fase (
    id integer NOT NULL,
    processo integer NOT NULL,
    responsavel integer NOT NULL,
    fase integer NOT NULL,
    data date NOT NULL,
    arquivo character varying(100),
    resposta character varying(250),
    oficial integer
);


ALTER TABLE public.processo_fase OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 18149)
-- Name: processo_fase_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.processo_fase_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.processo_fase_id_seq OWNER TO postgres;

--
-- TOC entry 2881 (class 0 OID 0)
-- Dependencies: 210
-- Name: processo_fase_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.processo_fase_id_seq OWNED BY public.processo_fase.id;


--
-- TOC entry 207 (class 1259 OID 18110)
-- Name: processos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.processos (
    id integer NOT NULL,
    juiz integer,
    advogadopromovente integer NOT NULL,
    promovente integer NOT NULL,
    promovido integer NOT NULL,
    advogadopromovido integer,
    status integer,
    data date NOT NULL
);


ALTER TABLE public.processos OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 18108)
-- Name: processos_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.processos_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.processos_id_seq OWNER TO postgres;

--
-- TOC entry 2882 (class 0 OID 0)
-- Dependencies: 206
-- Name: processos_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.processos_id_seq OWNED BY public.processos.id;


--
-- TOC entry 203 (class 1259 OID 17988)
-- Name: tipos_usuarios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tipos_usuarios (
    id integer NOT NULL,
    descricao character varying(50)
);


ALTER TABLE public.tipos_usuarios OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 17986)
-- Name: tipos_usuarios_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tipos_usuarios_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tipos_usuarios_id_seq OWNER TO postgres;

--
-- TOC entry 2883 (class 0 OID 0)
-- Dependencies: 202
-- Name: tipos_usuarios_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tipos_usuarios_id_seq OWNED BY public.tipos_usuarios.id;


--
-- TOC entry 205 (class 1259 OID 17998)
-- Name: usuarios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuarios (
    id integer NOT NULL,
    nome character varying(100) NOT NULL,
    email character varying(100) NOT NULL,
    senha character(32) NOT NULL,
    tipo integer NOT NULL
);


ALTER TABLE public.usuarios OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 17996)
-- Name: usuarios_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usuarios_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuarios_id_seq OWNER TO postgres;

--
-- TOC entry 2884 (class 0 OID 0)
-- Dependencies: 204
-- Name: usuarios_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.usuarios_id_seq OWNED BY public.usuarios.id;


--
-- TOC entry 2714 (class 2604 OID 18146)
-- Name: fases_processo id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fases_processo ALTER COLUMN id SET DEFAULT nextval('public.fases_processo_id_seq'::regclass);


--
-- TOC entry 2715 (class 2604 OID 18154)
-- Name: processo_fase id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.processo_fase ALTER COLUMN id SET DEFAULT nextval('public.processo_fase_id_seq'::regclass);


--
-- TOC entry 2713 (class 2604 OID 18113)
-- Name: processos id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.processos ALTER COLUMN id SET DEFAULT nextval('public.processos_id_seq'::regclass);


--
-- TOC entry 2711 (class 2604 OID 17991)
-- Name: tipos_usuarios id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipos_usuarios ALTER COLUMN id SET DEFAULT nextval('public.tipos_usuarios_id_seq'::regclass);


--
-- TOC entry 2712 (class 2604 OID 18001)
-- Name: usuarios id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios ALTER COLUMN id SET DEFAULT nextval('public.usuarios_id_seq'::regclass);


--
-- TOC entry 2872 (class 0 OID 18143)
-- Dependencies: 209
-- Data for Name: fases_processo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.fases_processo (id, descricao) FROM stdin;
1	informativa
2	deliberativa
3	aceito
4	negado
5	intimacao
6	encerramento
\.


--
-- TOC entry 2874 (class 0 OID 18151)
-- Dependencies: 211
-- Data for Name: processo_fase; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.processo_fase (id, processo, responsavel, fase, data, arquivo, resposta, oficial) FROM stdin;
150	100	3	1	2020-05-15	\N	\N	\N
\.


--
-- TOC entry 2870 (class 0 OID 18110)
-- Dependencies: 207
-- Data for Name: processos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.processos (id, juiz, advogadopromovente, promovente, promovido, advogadopromovido, status, data) FROM stdin;
100	1	3	2	5	4	1	2020-06-03
\.


--
-- TOC entry 2866 (class 0 OID 17988)
-- Dependencies: 203
-- Data for Name: tipos_usuarios; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tipos_usuarios (id, descricao) FROM stdin;
1	Juiz
2	Advogado
3	Parte
\.


--
-- TOC entry 2868 (class 0 OID 17998)
-- Dependencies: 205
-- Data for Name: usuarios; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuarios (id, nome, email, senha, tipo) FROM stdin;
2	Maria Joaquina	maria@gmail.com	202cb962ac59075b964b07152d234b70	3
3	Leonardo Barbosa	leonardo@gmail.com	202cb962ac59075b964b07152d234b70	2
4	Fernando Hoflinger	fernando@gmail.com	202cb962ac59075b964b07152d234b70	2
5	Joana Cardoso	joana@gmail.com	202cb962ac59075b964b07152d234b70	3
6	Sergio Moro	sergio@gmail.com	202cb962ac59075b964b07152d234b70	2
7	Luiz Inacio	luiz@gmail.com	202cb962ac59075b964b07152d234b70	3
8	Marcelo Odebretch	marcelo@gmail.com	202cb962ac59075b964b07152d234b70	3
1	Hiago Petris	hiago@gmail.com	202cb962ac59075b964b07152d234b70	1
\.


--
-- TOC entry 2885 (class 0 OID 0)
-- Dependencies: 208
-- Name: fases_processo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.fases_processo_id_seq', 6, true);


--
-- TOC entry 2886 (class 0 OID 0)
-- Dependencies: 210
-- Name: processo_fase_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.processo_fase_id_seq', 3, true);


--
-- TOC entry 2887 (class 0 OID 0)
-- Dependencies: 206
-- Name: processos_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.processos_id_seq', 2, true);


--
-- TOC entry 2888 (class 0 OID 0)
-- Dependencies: 202
-- Name: tipos_usuarios_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tipos_usuarios_id_seq', 3, true);


--
-- TOC entry 2889 (class 0 OID 0)
-- Dependencies: 204
-- Name: usuarios_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuarios_id_seq', 8, true);


--
-- TOC entry 2727 (class 2606 OID 18148)
-- Name: fases_processo fases_processo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fases_processo
    ADD CONSTRAINT fases_processo_pkey PRIMARY KEY (id);


--
-- TOC entry 2729 (class 2606 OID 18156)
-- Name: processo_fase processo_fase_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.processo_fase
    ADD CONSTRAINT processo_fase_pkey PRIMARY KEY (id);


--
-- TOC entry 2725 (class 2606 OID 18115)
-- Name: processos processos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.processos
    ADD CONSTRAINT processos_pkey PRIMARY KEY (id);


--
-- TOC entry 2717 (class 2606 OID 17995)
-- Name: tipos_usuarios tipos_usuarios_descricao_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipos_usuarios
    ADD CONSTRAINT tipos_usuarios_descricao_key UNIQUE (descricao);


--
-- TOC entry 2719 (class 2606 OID 17993)
-- Name: tipos_usuarios tipos_usuarios_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipos_usuarios
    ADD CONSTRAINT tipos_usuarios_pkey PRIMARY KEY (id);


--
-- TOC entry 2721 (class 2606 OID 18005)
-- Name: usuarios usuarios_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT usuarios_email_key UNIQUE (email);


--
-- TOC entry 2723 (class 2606 OID 18003)
-- Name: usuarios usuarios_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT usuarios_pkey PRIMARY KEY (id);


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


-- Completed on 2020-06-03 16:12:09

--
-- PostgreSQL database dump complete
--

