#!/bin/zsh

cd ~/server

echo "🔍 Checking for any process using port 43594..."
PID=$(lsof -ti :43594)

if [ -n "$PID" ]; then
    echo "🛑 Killing process on port 43594 (PID: $PID)..."
    kill -9 $PID
else
    echo "✅ No process using port 43594."
fi

echo "🧹 Cleaning and preparing build..."
mkdir -p bin
find ./src -name "*.java" > sources.txt
javac -cp "./libs/*" -d bin @sources.txt

if [ $? -ne 0 ]; then
    echo "❌ Compilation failed. Exiting."
    exit 1
fi

echo "🚀 Starting server in new screen session: vsncity"
screen -dmS vsncity java -cp "./libs/*:bin" com.rs.Launcher

echo "✅ Vengium Server Launched (screen: vsncity)"
