package com.jabs.hadoop;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.jabs.hadoop.functions.Map;
import com.jabs.hadoop.functions.Reduce;

public class WordCountDriver extends Configured implements Tool{
	
	public static void main(String[] args) {
		try {
			int exitCode = ToolRunner.run(new WordCountDriver(), args);
			System.exit(exitCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int run(String [] args) throws IOException, ClassNotFoundException, InterruptedException{
       //Create Job
   	   Configuration conf = getConf();
       Job job = Job.getInstance(conf);
       job.setJobName("WordCount Example");
       
       //Set jar, map & reduce functions
       job.setJarByClass(getClass());
       job.setMapperClass(Map.class);
       job.setReducerClass(Reduce.class);

       /*The setOutputKeyClass() and setOutputValueClass() methods control the output types
       for the reduce function, and must match what the Reduce class produces. The map output
       types default to the same types, so they do not need to be set if the mapper produces the
       same types as the reducer (as it does in our case). However, if they are different, the map
       output types must be set using the setMapOutputKeyClass() and
       setMapOutputValueClass() methods.*/
       job.setOutputKeyClass(Text.class);
       job.setOutputValueClass(IntWritable.class);
       job.setMapOutputKeyClass(Text.class);
       job.setMapOutputValueClass(IntWritable.class);
       
       //Set input & output format
       job.setInputFormatClass(TextInputFormat.class);
       job.setOutputFormatClass(TextOutputFormat.class);
       
       //Set input & output path
       FileInputFormat.addInputPath(job, new Path(args[0]));
       FileOutputFormat.setOutputPath(job, new Path(args[1]));
       
       job.setNumReduceTasks(1);
       
       //Execute the job
       return job.waitForCompletion(true) ? 0 : 1;
    }
}
