clear
echo "========================================================================="
echo ""
echo "[HTML List Generator Version 1.23, Copyright Mike Ryu]"
echo ""

echo "This script will generate a list of all HTML files in this direcotory."
echo "Below, you should be able to see a core Java source file that runs this."
echo ""

ls -lh *.java
echo ""
read -p "Would you like to run Java and generate the list? (Y/n) " -n 1 -r
echo ""

if [[ $REPLY =~ ^[Yy]$ ]]
then
   echo "Compiling Java core : javac FilterHtml.java"
   javac FilterHtml.java
   ls -la -R -f > AllFiles.txt
   java FilterHtml "AllFiles.txt"
   open "AllFiles.txt.filtered.txt"
else
   echo ""
   echo "Operation cancelled."
fi

echo "For feedback, please email: doryu@calpoly.edu."
echo ""
echo "========================================================================="
echo ""
