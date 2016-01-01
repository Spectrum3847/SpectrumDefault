#!/bin/sh
 case "$1" in
    start)
          # Start daemon.
          echo -e "Starting ADB:"
          /sbin/adb start-server
          ;;
    stop)
          # Stop daemons.
          echo -e "Shutting ADB:"
          /sbin/adb kill-server
          ;;
    restart)
          $0 stop
          $0 start
          ;;
    *)
          echo "Usage: $0 {start|stop|restart}"
          exit 1
 esac
 exit 0