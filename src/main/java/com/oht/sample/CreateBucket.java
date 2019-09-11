//snippet-sourcedescription:[CreateBucket.java demonstrates how to create a new S3 bucket.]
//snippet-keyword:[Java]
//snippet-sourcesyntax:[java]
//snippet-keyword:[Code Sample]
//snippet-keyword:[Amazon S3]
//snippet-keyword:[createBucket]
//snippet-service:[s3]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[2018-11-06]
//snippet-sourceauthor:[soo-aws]
/*
   Copyright 2010-2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.
   This file is licensed under the Apache License, Version 2.0 (the "License").
   You may not use this file except in compliance with the License. A copy of
   the License is located at
    http://aws.amazon.com/apache2.0/
   This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
   CONDITIONS OF ANY KIND, either express or implied. See the License for the
   specific language governing permissions and limitations under the License.
*/
package com.oht.sample;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;

/**
 * Create an Amazon S3 bucket.
 * 
 * This code expects that you have AWS credentials set up per:
 * http://docs.aws.amazon.com/java-sdk/latest/developer-guide/setup-credentials.html
 */
@Path("/s3")
public class CreateBucket {
		
	public static Map<String, String> getCredentials() {
		
		System.out.println("Check credentials");
		
		String Accesskeyid = AmazonS3ClientBuilder.standard().getCredentials().getCredentials().getAWSAccessKeyId();
		String Secretkey   = AmazonS3ClientBuilder.standard().getCredentials().getCredentials().getAWSSecretKey();
        HashMap<String, String> credentialMap = new HashMap<String, String>();
        credentialMap.put("AccessKeyid: ", Accesskeyid);
        credentialMap.put("Secretkey: ", Secretkey);
        System.out.println("credentials Access:"+ Accesskeyid);
        System.out.println("credentials Secret:"+ Secretkey);
		return credentialMap;
	}
	
	
    public static Bucket getBucket(String bucket_name) {
        
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
        		//.withCredentials(InstanceProfileCredentialsProvider.createAsyncRefreshingProvider(true) )
        		.withRegion(Regions.DEFAULT_REGION).build();
        
    	Bucket named_bucket = null;

        List<Bucket> buckets = s3.listBuckets();
        
        for (Bucket b : buckets) {
            if (b.getName().equals(bucket_name)) {
                named_bucket = b;
            }
        }
        return named_bucket;
    }


    public static Bucket createBucket(String bucket_name) {
        System.out.println("Checking if specified S3 bucket exists... ");
        
        Bucket b = null;
        
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
        		//.withCredentials(InstanceProfileCredentialsProvider.createAsyncRefreshingProvider(true) )
        		.withRegion(Regions.DEFAULT_REGION).build();
       
        System.out.println("S3 :"+ s3); 
        
        if (s3.doesBucketExistV2(bucket_name)) {
        	
            System.out.println("Bucket "+bucket_name+" already exists.");
            
            b = getBucket(bucket_name);
        
        } else {
            try {
            	System.out.format("Creating bucket: %s .\n", bucket_name);
                b = s3.createBucket(bucket_name);
            } catch (AmazonS3Exception e) {
                System.err.println(e.getErrorMessage());
            }
        }
        return b;
    }

    @Path("createBucket")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public HashMap<String, Object> get(@QueryParam("bucket_name") String bucket_name) throws Exception {

        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("BucketName", bucket_name);
        resultMap.put("env", System.getenv());
        resultMap.put("credentials", getCredentials());
        System.out.println(resultMap.toString());

        Bucket b = createBucket(bucket_name);

        if (b == null) {
            System.out.println("Error creating bucket!\n");
            resultMap.put("Result", "Error creating bucket!");
        } else {

            System.out.println("Done!\n");
            resultMap.put("Result", "Done!");
        }
        return resultMap;
    }
}