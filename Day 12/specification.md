{
    "openapi": "3.0.1",
    "info": {
        "title": "OpenAPI definition",
        "version": "v0"
    },
    "servers": [
        {
            "url": "http://localhost:8080",
            "description": "Generated server url"
        }
    ],
    "paths": {
        "/api/cycle/{id}/return": {
            "post": {
                "tags": [
                    "cycle-controller"
                ],
                "operationId": "returnCycle",
                "parameters": [
                    {
                        "name": "id",
                        "in": "path",
                        "required": true,
                        "schema": {
                            "type": "integer",
                            "format": "int64"
                        }
                    }
                ],
                "requestBody": {
                    "content": {
                        "application/json": {
                            "schema": {
                                "type": "object",
                                "additionalProperties": {
                                    "type": "integer",
                                    "format": "int32"
                                }
                            }
                        }
                    },
                    "required": true
                },
                "responses": {
                    "200": {
                        "description": "OK",
                        "content": {
                            "*/*": {
                                "schema": {
                                    "type": "string"
                                }
                            }
                        }
                    }
                }
            }
        },
        "/api/cycle/{id}/restock": {
            "post": {
                "tags": [
                    "cycle-controller"
                ],
                "operationId": "restockCycle",
                "parameters": [
                    {
                        "name": "id",
                        "in": "path",
                        "required": true,
                        "schema": {
                            "type": "integer",
                            "format": "int64"
                        }
                    }
                ],
                "requestBody": {
                    "content": {
                        "application/json": {
                            "schema": {
                                "type": "object",
                                "additionalProperties": {
                                    "type": "integer",
                                    "format": "int32"
                                }
                            }
                        }
                    },
                    "required": true
                },
                "responses": {
                    "200": {
                        "description": "OK",
                        "content": {
                            "*/*": {
                                "schema": {
                                    "type": "string"
                                }
                            }
                        }
                    }
                }
            }
        },
        "/api/cycle/{id}/borrow": {
            "post": {
                "tags": [
                    "cycle-controller"
                ],
                "operationId": "borrowCyclePostMapping",
                "parameters": [
                    {
                        "name": "id",
                        "in": "path",
                        "required": true,
                        "schema": {
                            "type": "integer",
                            "format": "int64"
                        }
                    }
                ],
                "requestBody": {
                    "content": {
                        "application/json": {
                            "schema": {
                                "type": "object",
                                "additionalProperties": {
                                    "type": "integer",
                                    "format": "int32"
                                }
                            }
                        }
                    },
                    "required": true
                },
                "responses": {
                    "200": {
                        "description": "OK",
                        "content": {
                            "*/*": {
                                "schema": {
                                    "type": "string"
                                }
                            }
                        }
                    }
                }
            }
        },
        "/api/cycle/add": {
            "post": {
                "tags": [
                    "cycle-controller"
                ],
                "operationId": "addCycle",
                "requestBody": {
                    "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/addNewCycle"
                            }
                        }
                    },
                    "required": true
                },
                "responses": {
                    "200": {
                        "description": "OK",
                        "content": {
                            "*/*": {
                                "schema": {
                                    "type": "string"
                                }
                            }
                        }
                    }
                }
            }
        },
        "/api/cycle/list": {
            "get": {
                "tags": [
                    "cycle-controller"
                ],
                "operationId": "listAvailableCycles",
                "responses": {
                    "200": {
                        "description": "OK",
                        "content": {
                            "*/*": {
                                "schema": {
                                    "type": "array",
                                    "items": {
                                        "$ref": "#/components/schemas/Cycle"
                                    }
                                }
                            }
                        }
                    }
                }
            }
        },
        "/api/cycle/": {
            "get": {
                "tags": [
                    "cycle-controller"
                ],
                "operationId": "listUsers",
                "responses": {
                    "200": {
                        "description": "OK",
                        "content": {
                            "*/*": {
                                "schema": {
                                    "type": "array",
                                    "items": {
                                        "$ref": "#/components/schemas/Cycle"
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    },
    "components": {
        "schemas": {
            "addNewCycle": {
                "type": "object",
                "properties": {
                    "brand": {
                        "type": "string"
                    },
                    "stock": {
                        "type": "integer",
                        "format": "int32"
                    }
                }
            },
            "Cycle": {
                "type": "object",
                "properties": {
                    "id": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "brand": {
                        "type": "string"
                    },
                    "stock": {
                        "type": "integer",
                        "format": "int32"
                    },
                    "numBorrowed": {
                        "type": "integer",
                        "format": "int32"
                    },
                    "numAvailable": {
                        "type": "integer",
                        "format": "int32"
                    }
                }
            }
        }
    }
}