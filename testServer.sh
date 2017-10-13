#!/usr/bin/env bash

echo "add item"
curl --data "item=cool item 1" http://localhost:8080/items
echo "get items"
curl -X "GET" http://localhost:8080/items
echo "delete item"
curl -X "DELETE" http://localhost:8080/items/0