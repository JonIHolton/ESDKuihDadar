# -*- coding: utf-8 -*-

import os

class Config(object):
    DEBUG = False
    # RabbitMQ Default Configuration
    RABBITMQ_HOST = 'rabbitmq-service'
    RABBITMQ_QUEUE = 'defaultQueue'
    RABBITMQ_WORKING_FLAG = 'N'

class DevelopmentConfig(Config):
    ENV = 'development'
    DEBUG = True
    # database
    SQLALCHEMY_DATABASE_URI = 'mysql://root:rootpassword@inventory-shipping-db:3306/inventory'
    SQLALCHEMY_ECHO = True
    # RabbitMQ Development Configuration
    RABBITMQ_HOST = 'rabbitmq-service'
    RABBITMQ_QUEUE = 'devQueue'
    RABBITMQ_WORKING_FLAG = 'N'

class StagingConfig(Config):
    ENV = 'staging'
    DEBUG = True
    # database
    SQLALCHEMY_DATABASE_URI = 'mysql://root:rootpassword@inventory-shipping-db:3306/inventory'
    SQLALCHEMY_ECHO = True
    # RabbitMQ Staging Configuration
    RABBITMQ_HOST = 'rabbitmq-service'
    RABBITMQ_QUEUE = 'stagingQueue'
    RABBITMQ_WORKING_FLAG = 'N'

class ProductionConfig(Config):
    ENV = 'production'
    DEBUG = False
    # database
    SQLALCHEMY_DATABASE_URI = 'mysql://root:rootpassword@inventory-shipping-db:3306/inventory'
    SQLALCHEMY_ECHO = True
    # RabbitMQ Production Configuration
    RABBITMQ_HOST = 'rabbitmq-service'
    RABBITMQ_QUEUE = 'prodQueue'
    RABBITMQ_WORKING_FLAG = 'N'

config = {
    'development': DevelopmentConfig,
    'production': ProductionConfig,
    'default': DevelopmentConfig
}
