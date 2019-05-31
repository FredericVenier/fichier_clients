package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SongsLoader {
    private static final String filePath = "res" + File.separator+ "data" +File.separator+ "json" + File.separator + "songs.json";

    public SongsLoader() {

    }

    public static void save(List<Song> songs) {
        JSONObject obj = new JSONObject();

        //on remplit un JSONArray avec les songs
        JSONArray jsonSongs = new JSONArray();
        for(int i=0; i<songs.size(); i++) {
            Song song = songs.get(i);
            JSONObject objSong = new JSONObject();
            objSong.put("name", song.getName());
            objSong.put("band", song.getBand());
            objSong.put("date", song.getDate());
            objSong.put("genre", song.getGenre());
            objSong.put("path", song.getPath());
            objSong.put("comments", song.getComments());
            objSong.put("unlocked", song.getUnlocked());
            objSong.put("cost", song.getCost());
            objSong.put("lyrics", song.getLyrics());
            objSong.put("nbOfStars", song.getNbOfStars());

            JSONArray jsonQuestions = new JSONArray();
            if(song.getQuestions() != null) {
                for (int j = 0; j < song.getQuestions().size(); j++) {
                    Question question = song.getQuestions().get(j);
                    JSONObject objQuestion = new JSONObject();
                    objQuestion.put("question", question.getQuestion());
                    objQuestion.put("rightAnswer", question.getRightAnswer());

                    JSONArray jsonStrings = new JSONArray();
                    for (int k = 0; k < question.getAnswers().size(); k++) {
                        String answer = question.getAnswers().get(k);
                        JSONObject objString = new JSONObject();
                        objString.put("answer", answer);

                        jsonStrings.add(objString);
                    }

                    objQuestion.put("answers", jsonStrings);

                    jsonQuestions.add(objQuestion);
                }
            }
            objSong.put("questions", jsonQuestions);

            JSONArray jsonVocabulary = new JSONArray();
            if(song.getVocabulary() != null) {
                for(int j=0; j<song.getVocabulary().size(); j++) {
                    Vocabulary voc = song.getVocabulary().get(j);
                    JSONObject objVoc = new JSONObject();
                    objVoc.put("word", voc.getWord());
                    objVoc.put("definition", voc.getDefinition());

                    jsonVocabulary.add(objVoc);
                }
            }
            objSong.put("vocabulary", jsonVocabulary);

            jsonSongs.add(objSong);
        }

        obj.put("songs", jsonSongs);

        try (FileWriter file = new FileWriter(filePath)) {
            file.write(obj.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Song> load() {
        List<Song> songs = new ArrayList<>();

        try {
            //on ouvre le json
            FileReader reader = new FileReader(filePath);

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

            //on recupere les songs
            JSONArray jsonsSongs = (JSONArray) jsonObject.get("songs");
            Iterator i = jsonsSongs.iterator();
            while (i.hasNext()) {
                JSONObject innerObj = (JSONObject) i.next();

                Song song = new Song();

                if(innerObj.get("name") != null) song.setName((String) innerObj.get("name"));
                if(innerObj.get("band") != null) song.setBand((String) innerObj.get("band"));
                if(innerObj.get("date") != null) song.setDate((String) innerObj.get("date"));
                if(innerObj.get("genre") != null) song.setGenre((String) innerObj.get("genre"));
                if(innerObj.get("lyrics") != null) song.setLyrics((String) innerObj.get("lyrics"));
                if(innerObj.get("path") != null) song.setPath((String) innerObj.get("path"));
                if(innerObj.get("comments") != null) song.setComments((String) innerObj.get("comments"));
                if(innerObj.get("unlocked") != null) song.setUnlocked((Boolean) innerObj.get("unlocked"));
                if(innerObj.get("cost") != null) song.setCost((int)(long) innerObj.get("cost"));
                if(innerObj.get("nbOfStars") != null) song.setNbOfStars((int)(long) innerObj.get("nbOfStars"));

                //on recupere les questions
                JSONArray jsonQuestions = (JSONArray) innerObj.get("questions");
                Iterator j = jsonQuestions.iterator();
                while (j.hasNext()) {
                    JSONObject innerObjQuestion = (JSONObject) j.next();
                    Question question = new Question();
                    if(innerObjQuestion.get("question") != null) question.setQuestion((String) innerObjQuestion.get("question"));
                    if(innerObjQuestion.get("rightAnswer") != null) question.setRightAnswerAuto((String) innerObjQuestion.get("rightAnswer"));

                    //on recupere les réponses
                    JSONArray jsonAnswers = (JSONArray) innerObjQuestion.get("answers");
                    Iterator k = jsonAnswers.iterator();
                    while (k.hasNext()) {
                        JSONObject innerObjAnswer = (JSONObject) k.next();
                        if(innerObjAnswer.get("answer") != null) {
                            String answer = (String) innerObjAnswer.get("answer");
                            question.addAnswers(answer);
                        }
                    }

                    song.addQuestions(question);
                }

                //on recupere le vocabulaire
                JSONArray jsonVocabulary = (JSONArray) innerObj.get("vocabulary");
                Iterator k = jsonVocabulary.iterator();
                while (k.hasNext()) {
                    JSONObject innerObjVoc = (JSONObject) k.next();
                    Vocabulary voc = new Vocabulary();
                    if(innerObjVoc.get("word") != null) voc.setWord((String) innerObjVoc.get("word"));
                    if(innerObjVoc.get("definition") != null) voc.setDefinition((String) innerObjVoc.get("definition"));

                    song.addVocabulary(voc);
                }

                songs.add(song);
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return songs;
    }

    public static void addSong(Song song) {
        List<Song> songs = load();
        songs.add(song);
        save(songs);
    }
}
