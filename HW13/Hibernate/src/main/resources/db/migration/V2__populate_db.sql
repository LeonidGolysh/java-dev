--Client
INSERT INTO Client (name) VALUES
    ('John Doe'),
    ('Alice Smith'),
    ('Bob Marley'),
    ('Will Smith'),
    ('Dart Weider'),
    ('Luke skywalker'),
    ('Steve Jobs'),
    ('Mark Zuckerberg'),
    ('Elon Mask'),
    ('Bib Bob');

--Planet
INSERT INTO Planet (id, name) VALUES
    ('MARS', 'Mars'),
    ('VEN', 'Venus'),
    ('EAR', 'Earth'),
    ('JUP', 'Jupiter');

--Ticket
INSERT INTO Ticket (created_at, client_id, from_planet_id, to_planet_id) VALUES
  ('2023-10-31T10:15:30Z', 1, 'VEN', 'EAR'),
  ('2023-10-30T12:30:45Z', 2, 'EAR', 'MARS'),
  ('2023-10-29T14:45:20Z', 3, 'MARS', 'JUP'),
  ('2023-10-28T16:10:10Z', 4, 'VEN', 'EAR'),
  ('2023-10-27T18:20:40Z', 5, 'MARS', 'JUP');