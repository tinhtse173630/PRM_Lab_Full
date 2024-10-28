-- Create the database if it does not already exist
IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'demo_prm_db')
    CREATE DATABASE demo_prm_db;
GO

-- Switch to the demo_prm_db database
USE demo_prm_db;
GO

-- Disable certain settings for compatibility
SET NOCOUNT ON;
SET XACT_ABORT ON;

-- Drop table if it already exists
IF OBJECT_ID('product', 'U') IS NOT NULL
    DROP TABLE product;

-- Create the table with MSSQL-specific types and settings
CREATE TABLE product (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(45) COLLATE Latin1_General_CI_AS NOT NULL,
    price FLOAT NOT NULL
);

-- Insert data into the table
INSERT INTO product (name, price) VALUES 
    ('Flavouring - Rum', 93876),
    ('Capers - Ox Eye Daisy', 75952),
    ('Scallops 60/80 Iqf', 14188),
    ('Turkey - Breast, Smoked', 12697),
    ('Beef - Top Butt Aaa', 44142),
    ('Tomatoes - Hot House', 15443),
    ('Sour Cream', 37646),
    ('Orange Roughy 6/8 Oz', 78042),
    ('Parsley - Dried', 82164),
    ('Brocolinni - Gaylan, Chinese', 32910);

-- Re-enable any settings that were turned off if necessary
SET NOCOUNT OFF;
