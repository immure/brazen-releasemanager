-- Artifact

CREATE TABLE bz_artifact
(
	id INT IDENTITY,
	artifactId VARCHAR(1000) NOT NULL,
	version VARCHAR(200) NOT NULL
);

CREATE INDEX idx_artifact_artifactId ON bz_artifact (artifactId)
;

-- Build

CREATE TABLE bz_build
(
	id INT IDENTITY,
	build_id VARCHAR(200) NOT NULL,
	version VARCHAR(200) NOT NULL,
	product INT NOT NULL
)
;

CREATE INDEX idx_build_buildid ON bz_build (build_id);
CREATE INDEX idx_build_version ON bz_build (build_id, version);
CREATE INDEX idx_build_product ON bz_build (product);

-- Product

CREATE TABLE bz_product
(
	id INT IDENTITY,
	product_name VARCHAR(200)
);

CREATE INDEX idx_product_productname ON bz_product (product_name);

-- Server

CREATE TABLE bz_server
(
	id INT identity,
	name VARCHAR(200) NOT NULL,
	address VARCHAR(200) NOT NULL,
	environment INT,
	status INT
);

CREATE INDEX idx_server_name ON bz_server (name);
CREATE INDEX idx_server_address ON bz_server(address);

-- Environment Type

CREATE TABLE bz_environment_type
(
	id INT IDENTITY,
	environment_type_name VARCHAR(100) NOT NULL
);

INSERT INTO bz_environment_type VALUES (0, 'STAGING');
INSERT INTO bz_environment_type VALUES (1, 'PRODUCTION');

-- Environment

CREATE TABLE bz_environment
(
	id INT IDENTITY,
	environment_name VARCHAR(200) NOT NULL,
	environment_type INT
);

CREATE INDEX idx_environment_name ON bz_environment (environment_name);

-- Deployment

CREATE TABLE bz_deployment
(
	id INT IDENTITY,
	deployment_time TIMESTAMP NOT NULL,
	server INT,
	build INT
);

CREATE INDEX idx_deployment_server ON bz_deployment (server);
CREATE INDEX idx_deployment_build ON bz_deployment (build);

-- User

CREATE TABLE bz_user
(
	id INT IDENTITY,
	screen_name VARCHAR(20) NOT NULL,
	name VARCHAR(100) NOT NULL,
	hashpw VARCHAR(100) NOT NULL
);

CREATE INDEX idx_user_screenname ON bz_user (screen_name);

-- ServerStatus

CREATE TABLE bz_serverstatus
(
	id INT IDENTITY,
	health VARCHAR(10) NOT NULL,
	warningMessage VARCHAR(1000)
);