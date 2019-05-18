# HadoopWordCount
This is a MapReduce program.
It counts the occurences of each word and so the output is word and its number of occurences.

Steps for execution:
1) Navigate to the directory HadoopWordCount\dataFiles.
2) Create input directory on HDFS:
    # hdfs dfs -mkdir -p /HadoopWordCount/input
3) Put the sample files to the input directory
    # hdfs dfs -put file1.txt /HadoopWordCount/input
    # hdfs dfs -put file2.txt /HadoopWordCount/input
4) Build the HadoopWordCount maven project.
   You can either import the project into eclipse & build it or if maven is installed on your laptop, you can build it from command prompt.
   For building from command prompt, navigate to the root directory of the project and fire the command
    # mvn clean install
 5) Navigate to the target directory on VM and run the mapreduce job
    # hadoop jar HadoopWordCountExample-0.0.1-SNAPSHOT.jar com.jabs.hadoop.WordCountDriver /HadoopWordCount/input /HadoopWordCount/output
 6) You can see the output using the command:
    # hdfs dfs -cat /HadoopWordCount/output/part-r-00000
	
	
Steps for executing Hadoop streaming program:
1) Navigate to the directory HadoopWordCount\dataFiles.
2) Create input directory on HDFS:
    # hdfs dfs -mkdir -p /HadoopWordCount/input
3) Put the sample files to the input directory
    # hdfs dfs -put file1.txt /HadoopWordCount/input
    # hdfs dfs -put file2.txt /HadoopWordCount/input
4) Copy python files from BigDataSample/HadoopWordCount/python/ to /home/hadoop
5) Execute the mapreduce job
     $ hadoop jar $HADOOP_HOME/share/hadoop/tools/lib/hadoop-streaming-2.9.2.jar \
       -file /home/hadoop/mapper.py    -mapper /home/hadoop/mapper.py \
       -file /home/hadoop/reducer.py   -reducer /home/hadoop/reducer.py \
       -input /HadoopWordCount/input/* -output /HadoopWordCount/output
6) You can see the output using the command:
    # hdfs dfs -cat /HadoopWordCount/output/part-r-00000