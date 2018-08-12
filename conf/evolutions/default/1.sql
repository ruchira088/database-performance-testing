
# ---!Ups
CREATE TABLE tiny_urls(
  key VARCHAR(16) PRIMARY KEY,
  created_at TIMESTAMP NOT NULL,
  url VARCHAR(2048) NOT NULL
)


# ---!Downs
DROP TABLE tiny_urls;