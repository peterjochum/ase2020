#!/bin/sh
# Setup the configuration for the steambuddy application on container start

# Check for required variables
if [ -z "$STEAMBUDDY_API_URL" ]; then
  echo "STEAMBUDDY_API_URL environment variable was not set - aborting ..."
  exit 1
fi

# Update config file
ASSETS_PATH="/usr/share/nginx/html/assets"
CONFIG_FILE="$ASSETS_PATH/config.json"
envsubst < "$ASSETS_PATH/config.sample.json" > "$CONFIG_FILE"

# Inform the user
echo "Using games API at: $STEAMBUDDY_API_URL"
echo "Updated $ASSETS_PATH/config.json"

# Debug
# cat $ASSETS_PATH/config.json
