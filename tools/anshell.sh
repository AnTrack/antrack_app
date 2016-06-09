#!/bin/bash

DEVICE=`basename $PWD`

function read_input() {
    echo -n "${DEVICE}> "
    read cmd
    echo "cmd $cmd" > control
}

read_input

while true; do
    change=$(inotifywait -q -e close_write,moved_to,create .)
    change=${change#./ * }
    if [ "$change" = "result" ]; then
	cat result
    fi
    read_input
done
