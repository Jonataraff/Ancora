CREATE TABLE cliente (
    id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    nome VARCHAR2(100) NOT NULL,
    email VARCHAR2(100) NOT NULL UNIQUE,
    telefone VARCHAR2(20),
    cpf VARCHAR2(14) NOT NULL UNIQUE,
    senha VARCHAR2(100) NOT NULL
);

-- Criação da tabela MECANICO
CREATE TABLE mecanicos (
    id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    nome VARCHAR2(100) NOT NULL,
    especialidade VARCHAR2(100),
    email VARCHAR2(100) NOT NULL UNIQUE,
    telefone VARCHAR2(20),		
    tipo_usuario_id NUMBER,
    senha VARCHAR2(100) NOT NULL,
    FOREIGN KEY (tipo_usuario_id) REFERENCES tipo_usuario(id)
);

-- Criação da tabela VEICULO
CREATE TABLE veiculos (
    id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    modelo VARCHAR2(100) NOT NULL,
    marca VARCHAR2(100) NOT NULL,
    placa VARCHAR2(10) NOT NULL UNIQUE,
    ano NUMBER(4),
    cliente_id NUMBER,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

-- Criação da tabela PECA
CREATE TABLE pecas (
    id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    nome VARCHAR2(100) NOT NULL,
    fabricante VARCHAR2(100),
    preco NUMBER(10, 2)
);