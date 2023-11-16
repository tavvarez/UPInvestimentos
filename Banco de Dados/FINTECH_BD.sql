
-- ==========================================================================
-- FIAP -ANALISE E DESENVOLVIMENTO DE SISTEMAS
-- TURMA 1TDSOD 2023
-- Vinicius Serafim Mesquita RM552081
-- Gabriel Sales Laborao
-- Rodrigo Arantes
-- Jenifer Scarlate Santana Domingues RM552331

-- Fase 06 capitulo 06 Armazenando e manipulando informacoes no Banco

-- ==========================================================================
-- ===== CREATE COMMANDS =====

-- CRIACAO DAS TABELAS:

-- tabela usuario usuarios do sistema
CREATE TABLE T_USER (
    cd_user         NUMBER(9) NOT NULL,
    nm_user         VARCHAR2(60) NOT NULL,
    nr_CPF          VARCHAR2(14) UNIQUE NOT NULL,
    dt_nasc         DATE NOT NULL,
    ds_email        VARCHAR2(50) NOT NULL,
    CONSTRAINT pk_T_USER PRIMARY KEY (cd_user)
);

--tabela conta corrente
CREATE TABLE T_CONTA_CC (
    cd_conta_cc     NUMBER(7) NOT NULL,
    vl_saldo_atual  NUMBER(9,2) NOT NULL,  
    cd_user         NUMBER(9) NOT NULL,
    CONSTRAINT pk_T_CONTA_CC PRIMARY KEY (cd_conta_cc),
    CONSTRAINT fk_T_CONTA_CC_CD_USER FOREIGN KEY (cd_user) REFERENCES T_USER (cd_user)
);

--tabela controle de gastos
CREATE TABLE T_CTRL_GASTO (
    cd_transacao    NUMBER(9) NOT NULL,
    vl_crtl_saldo   NUMBER(9,2) NOT NULL,
    cd_user         NUMBER(9) NOT NULL,
    CONSTRAINT pk_T_CTRL_GASTO PRIMARY KEY (cd_transacao),
    CONSTRAINT fk_T_CTRL_GASTO_CD_USER FOREIGN KEY (cd_user) REFERENCES T_USER(cd_user)
);
--tabela controle de gastos - saidas de valores
CREATE TABLE T_CTRL_GASTO_SAIDA (
    vl_saida        NUMBER(9,2)    NOT NULL,
    ds_saida        VARCHAR2(30),
    cd_transacao    NUMBER(10) UNIQUE NOT NULL,
    CONSTRAINT pk_T_CTRL_GASTO_SAIDA PRIMARY KEY (vl_saida), 
    CONSTRAINT fk_T_CTRL_SAIDA_T_CD_TRANSACAO FOREIGN KEY (cd_transacao) REFERENCES T_CTRL_GASTO(cd_transacao)
);

--tabela controle de gastos - entradas
CREATE TABLE T_CTRL_GASTO_ENTRADA (
    vl_entrada      NUMBER(9,2),
    ds_entrada      VARCHAR2(30),
    cd_transacao    NUMERIC(10),   
    CONSTRAINT pk_T_CTRL_GASTO_ENTRADA PRIMARY KEY (vl_entrada, cd_transacao),
    CONSTRAINT fk_T_CTRL_ENTRA_T_CD_TRANSACAO FOREIGN KEY (cd_transacao) REFERENCES T_CTRL_GASTO(cd_transacao)
);

--tabela investimentos - tipo investimentos

CREATE TABLE  T_INVEST_TIPO (

	cd_tipo_invest 		NUMBER(6),
	nm_tipo_invest		VARCHAR2(30),
	vl_taxa_indice		NUMBER(3,2),
	
	CONSTRAINT pk_T_INVEST_TIPO PRIMARY KEY (cd_tipo_invest)
);

--tabela investimentos
CREATE TABLE T_INVEST (
    nr_contrato         NUMBER(9),
    cd_tipo_invest      NUMBER(6),
    dt_inic_contrato    DATE,
    dt_lim_contrato     DATE,
    vl_investido        NUMBER(9,2),
    vl_taxa_rend_anual  NUMBER(3,2),
    vl_taxa_IRPF        NUMBER(3,2),
    vl_saldo_projecao   NUMBER(9,2),
    cd_user             NUMBER,
    
    CONSTRAINT pk_T_INVEST PRIMARY KEY (nr_contrato, dt_inic_contrato, cd_tipo_invest),
    CONSTRAINT fk_T_INVEST_T_CD_USER FOREIGN KEY (cd_user) REFERENCES T_USER(cd_user),
	CONSTRAINT fk_T_INVEST_T_INVEST_TIPO FOREIGN KEY (cd_tipo_invest) REFERENCES T_INVEST_TIPO (cd_tipo_invest)
);

-- Criacao de sequencia para o Codigo de transacao
CREATE SEQUENCE SQ_CD_TRANSACAO
START WITH 1
INCREMENT BY 1 
MAXVALUE 9999999 
MINVALUE  1
NOCYCLE
NOCACHE
ORDER;


--===========================================================================
-- ===== VIEW COMMANDS =====

-- Criacaoo da VIEW controle de gastos relatorio/report
CREATE VIEW VW_USER_REPORT AS
SELECT
    T_USER.cd_user,
    T_USER.nm_user,
    T_CTRL_GASTO.cd_transacao,
    T_INVEST.nr_contrato,
    T_CTRL_GASTO.vl_crtl_saldo
    FROM T_USER, T_CTRL_GASTO, T_INVEST;

DROP VIEW VW_USER_REPORT;   
CREATE VIEW VW_USER_REPORT AS
SELECT
    T_USER.cd_user AS USUARIO,
    T_USER.nm_user AS NOME,
    T_CTRL_GASTO.cd_transacao AS TRANSACOES,
    T_INVEST.nr_contrato AS CONTRATOS,
    T_CTRL_GASTO.vl_crtl_saldo AS SALDO_ATUAL
    FROM T_USER, T_CTRL_GASTO, T_INVEST;    
-- fim Criacaoo da VIEW controle de gastos relatorio/report
--===========================================================================
-- Selecao da VIEW de controle de gastos
SELECT * FROM VW_USER_REPORT;
-- Limpar VIEW de controle de gastos
DROP VIEW VW_USER_REPORT;

--===========================================================================
-- ===== INSERT COMMANDS =====

-- Inserir dados na Tabela T_USER
-- usuario joao
INSERT INTO T_USER (cd_user, nm_user, nr_CPF, dt_nasc, ds_email)
VALUES (1, 
'Joao Silva',
'12345678901',
TO_DATE('1990-01-15', 'YYYY-MM-DD'),
'joao.silva@email.com'
);
--usuario maria
INSERT INTO T_USER (cd_user, nm_user, nr_CPF, dt_nasc, ds_email)
VALUES (2,
'Maria Souza', 
'98765432109', 
TO_DATE('1985-07-20', 'YYYY-MM-DD'), 
'maria.souza@email.com'
);


-- Inserir dados na Tabela T_CONTA_CC 
-- usuario MARIA cd_user 1
INSERT INTO T_CONTA_CC (cd_conta_cc, vl_saldo_atual, cd_user)
SELECT
    10001,
    15000.50,
    cd_user
FROM T_USER
WHERE cd_user = 2;


-- Inserir dados na Tabela T_CONTA_CC 
-- usuario JOAO cd_user 1
INSERT INTO T_CONTA_CC (cd_conta_cc, vl_saldo_atual, cd_user)
SELECT
    10002,
    15000.50,
    cd_user
FROM T_USER
WHERE cd_user = 1;


-- Inserir dados na Tabela T_CTRL_GASTO
-- -- usuario JOAO cd_user 1
INSERT INTO T_CTRL_GASTO (cd_transacao, vl_crtl_saldo, cd_user)
SELECT
10401, -- Ou SQ_CD_TRANCAO.NEXTVAL Usado valor fixo apenas para exemplo/visualizacao
500.00, 
cd_user
FROM T_USER
WHERE cd_user = 1;
INSERT INTO T_CTRL_GASTO (cd_transacao, vl_crtl_saldo, cd_user)
SELECT
10403, -- Ou user SQ_CD_TRANCAO.NETVAL Usado valor fixo apenas para exemplo
500.00, 
cd_user
FROM T_USER
WHERE cd_user = 1;

-- -- usuario MARIA cd_user 2
INSERT INTO T_CTRL_GASTO (cd_transacao, vl_crtl_saldo, cd_user)
SELECT
10402, 
502.00, 
cd_user
FROM T_USER
WHERE cd_user = 2;
INSERT INTO T_CTRL_GASTO (cd_transacao, vl_crtl_saldo, cd_user)
SELECT
10404, 
502.00, 
cd_user
FROM T_USER
WHERE cd_user = 2;


-- Inserir dados na Tabela T_CTRL_GASTO_SAIDA
-- -- usuario JOAO cd_user 1
INSERT INTO T_CTRL_GASTO_SAIDA (vl_saida, ds_saida, cd_transacao)
SELECT 
111.00,
'saida money',
cd_transacao
FROM T_CTRL_GASTO
WHERE cd_transacao = 10401 ;

-- -- usuario MARIA cd_user 2
INSERT INTO T_CTRL_GASTO_SAIDA (vl_saida, ds_saida, cd_transacao)
SELECT 
222.00,
'saida money',
cd_transacao
FROM T_CTRL_GASTO
WHERE cd_transacao = 10402;

COMMIT;
SELECT * FROM T_CTRL_GASTO;
SELECT * FROM T_CTRL_GASTO_SAIDA;

-- Inserir dados na Tabela T_CTRL_GASTO_ENTRADA
-- -- usuario JOAO cd_user 1 - transacao 10403
INSERT INTO T_CTRL_GASTO_ENTRADA (vl_entrada, ds_entrada, cd_transacao)
SELECT 
911.00,
'entrada money',
cd_transacao
FROM T_CTRL_GASTO
WHERE cd_transacao = 10403;

-- -- usuario MARIA cd_user 2 transacao 10404
INSERT INTO T_CTRL_GASTO_ENTRADA (vl_entrada, ds_entrada, cd_transacao)
SELECT 
922.00,
'gain money',
cd_transacao
FROM T_CTRL_GASTO
WHERE cd_transacao = 10404;

COMMIT;
SELECT * FROM T_CTRL_GASTO;
SELECT * FROM T_CTRL_GASTO_SAIDA;

-- Inserir dados na Tabela T_INVEST_TIPO
INSERT INTO T_INVEST_TIPO (cd_tipo_invest, nm_tipo_invest, vl_taxa_indice)
VALUES (
1, 
'Renda Fixa', 
0.05
);

INSERT INTO T_INVEST_TIPO (cd_tipo_invest, nm_tipo_invest, vl_taxa_indice)
VALUES (
2, 
'Acoes', 
0.07
);


-- Inserir dados na Tabela T_INVEST
-- investimentos usuario 01 Joao
INSERT INTO T_INVEST (nr_contrato, cd_tipo_invest, dt_inic_contrato, dt_lim_contrato, vl_investido, vl_taxa_rend_anual, vl_taxa_IRPF, vl_saldo_projecao, cd_user)
SELECT
20001, 
2, 
TO_DATE('2023-01-01', 'YYYY-MM-DD'), 
TO_DATE('2023-12-31', 'YYYY-MM-DD'), 
5000.00, 
0.05, 
0.15, 
5250.00, 
cd_user
FROM T_CONTA_CC
WHERE cd_user = 1;
INSERT INTO T_INVEST (nr_contrato, cd_tipo_invest, dt_inic_contrato, dt_lim_contrato, vl_investido, vl_taxa_rend_anual, vl_taxa_IRPF, vl_saldo_projecao, cd_user)
SELECT
20003, 
2, 
TO_DATE('2023-01-02', 'YYYY-MM-DD'), 
TO_DATE('2023-12-31', 'YYYY-MM-DD'), 
5000.33, 
0.05, 
0.15, 
5250.00, 
cd_user
FROM T_CONTA_CC
WHERE cd_user = 1;
-- Usucario codigo 02 Maria
INSERT INTO T_INVEST (nr_contrato, cd_tipo_invest, dt_inic_contrato, dt_lim_contrato, vl_investido, vl_taxa_rend_anual, vl_taxa_IRPF, vl_saldo_projecao, cd_user)
SELECT
20002, 
2, 
TO_DATE('2023-01-03', 'YYYY-MM-DD'), 
TO_DATE('2023-12-31', 'YYYY-MM-DD'), 
7.55, 
0.05, 
0.15, 
7250.32, 
cd_user
FROM T_CONTA_CC
WHERE cd_user = 2;
INSERT INTO T_INVEST (nr_contrato, cd_tipo_invest, dt_inic_contrato, dt_lim_contrato, vl_investido, vl_taxa_rend_anual, vl_taxa_IRPF, vl_saldo_projecao, cd_user)
SELECT
20004, 
2, 
TO_DATE('2023-01-04', 'YYYY-MM-DD'), 
TO_DATE('2023-12-31', 'YYYY-MM-DD'), 
701.44, 
0.05, 
0.15, 
7250.32, 
cd_user
FROM T_CONTA_CC
WHERE cd_user = 2;

SELECT * FROM T_INVEST;



--===========================================================================
-- ===== COMANDOS DE CONSULTA =====

-- 1 Consultar os dados de um usuario (filtrar a partir do seu codigo):

--SELECT * FROM T_USER WHERE cd_user = [codigo do usuario;
SELECT * FROM T_USER WHERE cd_user = 1;
SELECT * FROM T_USER WHERE cd_user = 2;


-- 2 Consultar os dados de um unico registro de despesa de um usuario (filtrar a partir do codigo do usuario e do codigo da despesa):

-- Comando exbibir UNICO registro de despesa do usuario 1 Joao
-- Transacao 10401 saida valor / 10403 entrada valor
SELECT * FROM T_CTRL_GASTO WHERE cd_user = 1 AND cd_transacao = 10401;
SELECT * FROM T_CTRL_GASTO WHERE cd_user = 1 AND cd_transacao = 10403;
-- Comando exbibir UNICO registro de despesa do usuario 2 Mariao
-- Transacao 10402 saida valor / 10404 entrada valor
SELECT * FROM T_CTRL_GASTO WHERE cd_user = 2 AND cd_transacao = 10402;
SELECT * FROM T_CTRL_GASTO WHERE cd_user = 2 AND cd_transacao = 10404;

-- 3 Consultar os dados de todos os registros de despesas de um usuário, ordenando-os dos registros mais recentes para os mais antigos (filtrar a partir do seu codigo):

-- Comando EXIBIBIR TODAS AS TRANSACOES para o usuario 1 - Joao
SELECT * FROM T_CTRL_GASTO WHERE cd_user = 1 ORDER BY cd_transacao DESC;
-- Comando EXIBIBIR TODAS AS TRANSACOES para o usuario 2 - Maria
SELECT * FROM T_CTRL_GASTO WHERE cd_user = 2 ORDER BY cd_transacao DESC;

-- 5 Consultar os dados um unico regristro de investimento de um usuario
--Filtrar a aprtir do codigo do usuario e do codigo de investimento:
-- Usuario codigo 01 Joao (cd investimento = numero do contrato)
SELECT * FROM T_INVEST WHERE cd_user = 1 AND nr_contrato = 20001;
-- Usuario codigo 02 Maria (cd investimento = numero do contrato)
SELECT * FROM T_INVEST WHERE cd_user = 2 AND nr_contrato = 20002;

-- 6 Consultar os dados um unico regristro de investimento de um usuario
--ordenando os registros dos mais recentes para os mais antigos.
--OBSERVACAO: desta vez filtrado por data. Pode-se tambem ser filtrado por 
--numero docontrato pois � sequencial.
-- Usuario codigo 01 Joao (cd investimento = numero do contrato)
SELECT * FROM T_INVEST WHERE cd_user = 1 ORDER BY dt_inic_contrato DESC;
-- Usuario codigo 02 Maria (cd investimento = numero do contrato)
SELECT * FROM T_INVEST WHERE cd_user = 2 ORDER BY dt_inic_contrato DESC;

-- 7 Consultar os dados basicos de um usuario, o ultimo investimento registrado
-- e a ultima despesa registrada (filtrar a partir do codigo de usuario
-- consulta necessaria para o dashboard):

-- Usuario codigo 01 Joao ultimo contrato de investimento e ultima transacao
SELECT DISTINCT
    LAST_VALUE(T_INVEST.NR_CONTRATO) OVER (ORDER BY T_INVEST.NR_CONTRATO ASC RANGE BETWEEN UNBOUNDED PRECEDING AND UNBOUNDED FOLLOWING) AS ULT_CONTRATO_INVEST,
    LAST_VALUE(T_CTRL_GASTO.CD_TRANSACAO) OVER (ORDER BY T_CTRL_GASTO.CD_TRANSACAO ASC RANGE BETWEEN UNBOUNDED PRECEDING AND UNBOUNDED FOLLOWING) AS ULT_TRANSACAO
FROM T_INVEST, T_CTRL_GASTO
WHERE T_INVEST.CD_USER = 1;

-- Usuario codigo 02 Maria ultimo contrato de investimento e ultima transacao
SELECT DISTINCT
    LAST_VALUE(T_INVEST.NR_CONTRATO) OVER (ORDER BY T_INVEST.NR_CONTRATO ASC RANGE BETWEEN UNBOUNDED PRECEDING AND UNBOUNDED FOLLOWING) AS ULT_CONTRATO_INVEST,
    LAST_VALUE(T_CTRL_GASTO.CD_TRANSACAO) OVER (ORDER BY T_CTRL_GASTO.CD_TRANSACAO ASC RANGE BETWEEN UNBOUNDED PRECEDING AND UNBOUNDED FOLLOWING) AS ULT_TRANSACAO
FROM T_INVEST, T_CTRL_GASTO
WHERE T_INVEST.CD_USER = 2;
--==========================================================================
-- ===== UPDATE COMMANDS =====

-- Alterar todos os dados do usuario, utilizando seu codigo como referencia.
UPDATE T_USER SET 
nm_user = 'Jhon Silva',  -- Novo Nome - obs: Exemplo
nr_CPF = '321.654.987.45',  -- Novo CPF - obs: Exemplo
dt_nasc = TO_DATE('31/12/1989', 'DD/MM/YYYY'),  -- Nova data de nascimento - obs: Exemplo
ds_email = 'novojhonny@gmail.com'  -- Novo e-mail - obs: Exemplo
WHERE cd_user = 1;  -- Alteracoes serao aplicadas ao usuario com codigo 1

--Alterar todos os dados das despesas do usuario, utilizando o codigo como referencia.
UPDATE T_CTRL_GASTO
SET vl_crtl_saldo = 75.00
WHERE cd_transacao =10401  
AND cd_user = 1;

--Alterar todos os dados para investimentos do usuario, utilizando o codigo como referencia:
UPDATE T_INVEST
SET vl_investido = 6000.00, 
vl_taxa_rend_anual = 0.06
WHERE nr_contrato = 20001 
AND cd_user = 1;

--END
--==========================================================================
