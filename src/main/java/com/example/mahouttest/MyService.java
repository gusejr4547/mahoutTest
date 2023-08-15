package com.example.mahouttest;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.math.hadoop.similarity.cooccurrence.measures.CosineSimilarity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service

public class MyService {

    public List<RecommendedItem> getItems(long memberId) throws IOException, TasteException {
        FileDataModel dm = new FileDataModel(new File("data.csv"));
//        DataModel dataModel = new MySQLJDBCDataModel()
//        ItemSimilarity similarity = new CosineSimilarity(new Recommendation);
        LogLikelihoodSimilarity sim = new LogLikelihoodSimilarity(dm);

        GenericItemBasedRecommender recommender = new GenericItemBasedRecommender(dm, sim);
        List<RecommendedItem> recommend = recommender.recommend(memberId, 10);
        System.out.println("recommend = " + recommend);
        for(RecommendedItem item : recommend){
            long postId = item.getItemID();
            float value = item.getValue();
            System.out.println("themeCode = " + postId + " value = " + value);
        }

        return recommend;
    }
}
