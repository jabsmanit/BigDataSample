package com.jabs.hadoop.functions;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import com.jabs.hadoop.writable.TextPair;

public class Reduce extends Reducer<Text, IntWritable, TextPair, IntWritable> {
	@Override
    public void reduce(Text key, Iterable<IntWritable> values, Context context) 
      throws IOException, InterruptedException {
        int sum = 0;
        for (IntWritable val : values) {
            sum += val.get();
        }
        context.write(new TextPair("count of", key.toString()), new IntWritable(sum));
    }
 }
