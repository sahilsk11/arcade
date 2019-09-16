cd src
javac *.java > error.log
clear
echo "Building arcade..."
sleep 1
echo "Waking up the queen..."
sleep 1
echo "Hacking into system root- uhh I mean cleaning directory..."
cat images/console.txt

echo
echo "How many tokens do you want to insert?"
read -p "> " choice
if [ $choice -eq 0 ]
then
    echo "Buy more tokens!"
    exit
else
    echo "Depositing $choice tokens..."
fi

if [ $choice -eq 1 ] 
then
    echo "Starting Slap Jack!"
    sleep 2
    clear
    java SlapJack
    cd ..
    exit
elif [ $choice -eq 2 ]
then
    echo "Sorry, this isn't available yet."
    cd ..
    exit
elif [ $choice -eq 3 ]
then
    echo "Starting Magic Jacques!"
    sleep 2
    clear
    java MagicJacques
    cd ..
    exit
else
    echo "Goodbye 👋"
    cd ..
fi
