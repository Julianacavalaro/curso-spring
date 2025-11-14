-- Inserts de usuários
INSERT INTO usuario (uuid, nome, email, dob) VALUES
  ('550e8400-e29b-41d4-a716-446655440000', 'João Silva', 'joao.silva@example.com', '1990-01-10'),
  ('550e8400-e29b-41d4-a716-446655440001', 'Maria Oliveira', 'maria.oliveira@example.com', '1992-03-15'),
  ('550e8400-e29b-41d4-a716-446655440002', 'Pedro Santos', 'pedro.santos@example.com', '1985-07-22'),
  ('550e8400-e29b-41d4-a716-446655440003', 'Ana Costa', 'ana.costa@example.com', '1988-12-03'),
  ('550e8400-e29b-41d4-a716-446655440004', 'Carlos Pereira', 'carlos.pereira@example.com', '1995-05-09'),
  ('550e8400-e29b-41d4-a716-446655440005', 'Paula Souza', 'paula.souza@example.com', '1993-11-17'),
  ('550e8400-e29b-41d4-a716-446655440006', 'Rafael Lima', 'rafael.lima@example.com', '1987-09-23'),
  ('550e8400-e29b-41d4-a716-446655440007', 'Beatriz Rocha', 'beatriz.rocha@example.com', '1998-04-30'),
  ('550e8400-e29b-41d4-a716-446655440008', 'Lucas Fernandes', 'lucas.fernandes@example.com', '1994-02-19'),
  ('550e8400-e29b-41d4-a716-446655440009', 'Fernanda Lima', 'fernanda.lima@example.com', '1991-08-14');

-- Inserts de postagens
INSERT INTO postagem (uuid, titulo, corpo, data_criacao, usuario_id) VALUES
  ('660e8400-e29b-41d4-a716-446655440000', 'Primeira Postagem', 'Este é o corpo da primeira postagem', NOW(), 1),
  ('660e8400-e29b-41d4-a716-446655440001', 'Segunda Postagem', 'Conteúdo da segunda postagem', NOW(), 2),
  ('660e8400-e29b-41d4-a716-446655440002', 'Terceira Postagem', 'Texto da terceira postagem', NOW(), 3),
  ('660e8400-e29b-41d4-a716-446655440003', 'Quarta Postagem', 'Corpo da quarta postagem', NOW(), 4),
  ('660e8400-e29b-41d4-a716-446655440004', 'Quinta Postagem', 'Texto da quinta postagem', NOW(), 5),
  ('660e8400-e29b-41d4-a716-446655440005', 'Sexta Postagem', 'Conteúdo da sexta postagem', NOW(), 6),
  ('660e8400-e29b-41d4-a716-446655440006', 'Sétima Postagem', 'Corpo da sétima postagem', NOW(), 7),
  ('660e8400-e29b-41d4-a716-446655440007', 'Oitava Postagem', 'Texto da oitava postagem', NOW(), 8),
  ('660e8400-e29b-41d4-a716-446655440008', 'Nona Postagem', 'Corpo da nona postagem', NOW(), 9),
  ('660e8400-e29b-41d4-a716-446655440009', 'Décima Postagem', 'Conteúdo da décima postagem', NOW(), 10);
