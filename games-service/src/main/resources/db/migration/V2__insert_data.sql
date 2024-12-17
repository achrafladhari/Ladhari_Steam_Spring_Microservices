-- Insert categories for games
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'High-octane action-packed games', 'Action');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Immersive story-driven adventures', 'Adventure');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Role-playing games with character progression', 'RPG');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Realistic or fantasy simulations', 'Simulation');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Competitive and recreational sports games', 'Sports');

-- Insert games for the 'Action' category
INSERT INTO public.games (id, avaiblity, description, name, price, category_id)
VALUES
    (nextval('games_seq'), 50, 'Fast-paced shooting game with multiplayer mode', 'Rapid Fire', 59.99, (SELECT id FROM category WHERE name = 'Action')),
    (nextval('games_seq'), 30, 'Open-world game with intense combat mechanics', 'Battle Horizon', 69.99, (SELECT id FROM category WHERE name = 'Action')),
    (nextval('games_seq'), 40, 'Stealth-action game with a gripping narrative', 'Shadow Operative', 49.99, (SELECT id FROM category WHERE name = 'Action')),
    (nextval('games_seq'), 35, 'Survival game in a zombie apocalypse', 'Dead Zone', 39.99, (SELECT id FROM category WHERE name = 'Action')),
    (nextval('games_seq'), 25, 'Arcade-style brawler with local co-op', 'Street Rumble', 19.99, (SELECT id FROM category WHERE name = 'Action'));

-- Insert games for the 'Adventure' category
INSERT INTO public.games (id, avaiblity, description, name, price, category_id)
VALUES
    (nextval('games_seq'), 40, 'Mystery-solving game set in an ancient city', 'Lost Chronicles', 49.99, (SELECT id FROM category WHERE name = 'Adventure')),
    (nextval('games_seq'), 28, 'Fantasy adventure with magical realms', 'Enchanted Quest', 59.99, (SELECT id FROM category WHERE name = 'Adventure')),
    (nextval('games_seq'), 22, 'Sci-fi exploration game with alien worlds', 'Galactic Odyssey', 69.99, (SELECT id FROM category WHERE name = 'Adventure')),
    (nextval('games_seq'), 35, 'Cinematic adventure with decision-based gameplay', 'Life Choices', 39.99, (SELECT id FROM category WHERE name = 'Adventure')),
    (nextval('games_seq'), 30, 'Interactive narrative game with time travel', 'Chrono Shift', 29.99, (SELECT id FROM category WHERE name = 'Adventure'));

-- Insert games for the 'RPG' category
INSERT INTO public.games (id, avaiblity, description, name, price, category_id)
VALUES
    (nextval('games_seq'), 20, 'Epic fantasy RPG with customizable characters', 'Dragon Realm', 69.99, (SELECT id FROM category WHERE name = 'RPG')),
    (nextval('games_seq'), 18, 'Cyberpunk RPG with a futuristic city setting', 'Neon Dreams', 59.99, (SELECT id FROM category WHERE name = 'RPG')),
    (nextval('games_seq'), 25, 'Post-apocalyptic RPG with survival elements', 'Wasteland Wanderer', 49.99, (SELECT id FROM category WHERE name = 'RPG')),
    (nextval('games_seq'), 30, 'Space RPG with galaxy exploration and battles', 'Starbound Legacy', 79.99, (SELECT id FROM category WHERE name = 'RPG')),
    (nextval('games_seq'), 22, 'Turn-based RPG with a deep storyline', 'Tactics Legends', 39.99, (SELECT id FROM category WHERE name = 'RPG'));

-- Insert games for the 'Simulation' category
INSERT INTO public.games (id, avaiblity, description, name, price, category_id)
VALUES
    (nextval('games_seq'), 45, 'City-building simulation with sandbox mode', 'Urban Architect', 29.99, (SELECT id FROM category WHERE name = 'Simulation')),
    (nextval('games_seq'), 35, 'Farming simulation with co-op multiplayer', 'Harvest Dreams', 19.99, (SELECT id FROM category WHERE name = 'Simulation')),
    (nextval('games_seq'), 28, 'Flight simulator with realistic physics', 'Sky Captain', 59.99, (SELECT id FROM category WHERE name = 'Simulation')),
    (nextval('games_seq'), 32, 'Life simulation game with character customization', 'Virtual Life', 49.99, (SELECT id FROM category WHERE name = 'Simulation')),
    (nextval('games_seq'), 25, 'Business tycoon game with strategy elements', 'Enterprise Builder', 39.99, (SELECT id FROM category WHERE name = 'Simulation'));

-- Insert games for the 'Sports' category
INSERT INTO public.games (id, avaiblity, description, name, price, category_id)
VALUES
    (nextval('games_seq'), 40, 'Soccer simulation game with realistic physics', 'Soccer Pro', 59.99, (SELECT id FROM category WHERE name = 'Sports')),
    (nextval('games_seq'), 30, 'Basketball game with detailed team management', 'Hoop Stars', 49.99, (SELECT id FROM category WHERE name = 'Sports')),
    (nextval('games_seq'), 25, 'Extreme skateboarding game with tricks', 'Skate Rush', 29.99, (SELECT id FROM category WHERE name = 'Sports')),
    (nextval('games_seq'), 35, 'Tennis game with local and online multiplayer', 'Grand Slam', 39.99, (SELECT id FROM category WHERE name = 'Sports')),
    (nextval('games_seq'), 28, 'Racing game with customizable cars', 'Speed Kings', 69.99, (SELECT id FROM category WHERE name = 'Sports'));
