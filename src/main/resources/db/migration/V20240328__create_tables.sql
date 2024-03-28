CREATE TABLE IF NOT EXISTS Mesa(
	id BIGSERIAL PRIMARY KEY,
	titulo varchar(100) NOT NULL,
	status varchar(22) NOT NULL
);

CREATE TABLE IF NOT EXISTS Categoria(
	id BIGSERIAL PRIMARY KEY,
	Categoria VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Reserva(
	id BIGSERIAL PRIMARY KEY,
	id_mesa INTEGER REFERENCES Mesa(id),
	qtde_participantes INTEGER,
	total_gasto FLOAT,
	data_abertura TIMESTAMP,
	data_fechamento TIMESTAMP
);

CREATE TABLE IF NOT EXISTS Item(
	id BIGSERIAL PRIMARY KEY,
	nome VARCHAR(200),
	id_categoria INTEGER REFERENCES Categoria(id),
	preco FLOAT
);

CREATE TABLE IF NOT EXISTS Pedido(
	id BIGSERIAL PRIMARY KEY,
	id_reserva INTEGER REFERENCES Reserva(id),
	nome_cliente VARCHAR(200),
	status VARCHAR(22)
);

CREATE TABLE IF NOT EXISTS item_quantidade(
	id BIGSERIAL PRIMARY KEY,
	id_pedido INTEGER REFERENCES Pedido(id),
	id_item INTEGER REFERENCES Item(id),
	quantidade INTEGER
);