# -*- coding: utf-8 -*-

import os

class Config(object):
    DEBUG = False
    # mail settings
    MAIL_SERVER = 'smtp.126.com'
    MAIL_PORT = 25
    # mail authentication
    MAIL_USERNAME = 'bababa'
    MAIL_PASSWORD = 'bababa'
    # RabbitMQ Default Configuration
    RABBITMQ_HOST = 'localhost'
    RABBITMQ_QUEUE = 'defaultQueue'
    RABBITMQ_WORKING_FLAG = 'N'

class DevelopmentConfig(Config):
    ENV = 'development'
    DEBUG = True
    # database
    SQLALCHEMY_DATABASE_URI = 'mysql://root@127.0.0.1:3306/shippingrecord'
    SQLALCHEMY_ECHO = True
    # RabbitMQ Development Configuration
    RABBITMQ_HOST = 'dev.rabbitmq.host'
    RABBITMQ_QUEUE = 'devQueue'
    RABBITMQ_WORKING_FLAG = 'N'

class StagingConfig(Config):
    ENV = 'staging'
    DEBUG = True
    # database
    SQLALCHEMY_DATABASE_URI = 'mysql://root@127.0.0.1:3306/shippingrecord'
    SQLALCHEMY_ECHO = True
    # RabbitMQ Staging Configuration
    RABBITMQ_HOST = 'staging.rabbitmq.host'
    RABBITMQ_QUEUE = 'stagingQueue'
    RABBITMQ_WORKING_FLAG = 'N'

class ProductionConfig(Config):
    ENV = 'production'
    DEBUG = False
    # database
    SQLALCHEMY_DATABASE_URI = 'mysql://root@host.docker.internal:3306/shippingrecord'
    SQLALCHEMY_ECHO = True
    # RabbitMQ Production Configuration
    RABBITMQ_HOST = 'prod.rabbitmq.host'
    RABBITMQ_QUEUE = 'prodQueue'
    RABBITMQ_WORKING_FLAG = 'N'

config = {
    'development': DevelopmentConfig,
    'production': ProductionConfig,
    'default': DevelopmentConfig
}
