CREATE TABLE usuario (
	id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY,
	nome VARCHAR(60) NOT NULL,
	endereco VARCHAR(100) NOT NULL,
	cpf VARCHAR(20) NOT NULL,
	telefone VARCHAR(15) NOT NULL,
	login VARCHAR(20) NOT NULL,
	senha VARCHAR(20) NOT NULL,
	permissao VARCHAR(20) NOT NULL,
);

CREATE TABLE alimento (
    id_alimento INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY,
    nome_alimento VARCHAR(60) NOT NULL,
    data_validade TIMESTAMP NOT NULL,
	qtd_estoque INTEGER NOT NULL,
    tipo VARCHAR(20) NOT NULL,
    id_usuario INTEGER,
    FOREIGN KEY (id_usuario) references usuario(id),
);

CREATE TABLE beneficiado (
    id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY,
    nome VARCHAR(60) NOT NULL,
    profissao VARCHAR(60) NOT NULL,
	telefone VARCHAR(60) NOT NULL,
    data_nascimento TIMESTAMP NOT NULL,
	endereco VARCHAR(100) NOT NULL,
	numero INTEGER NOT NULL,
	bairro VARCHAR(50) NOT NULL,
	cidade VARCHAR(50) NOT NULL,
	descricao VARCHAR(1024) NOT NULL,
    id_usuario INTEGER,
    FOREIGN KEY (id_usuario) references usuario(id),
);

