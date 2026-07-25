#!/bin/bash

if [ $# -ne 1 ]; then
    echo "Usage: $0 <log_file>"
    exit 1
fi

sed -E -i \
-e 's/[0-9]{4}-?[0-9]{4}-?[0-9]{4}-?[0-9]{4}/XXXX-XXXX-XXXX-XXXX/g' \
-e 's/HTTP\/1\.0/HTTP\/1.1/g' \
-e '/^#/d' \
"$1"

echo "Cleanup completed for $1"
