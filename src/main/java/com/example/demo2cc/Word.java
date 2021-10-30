package com.example.demo2cc;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;


public class Word {
    private String Word;
    private String Data;
    private String Description;
    private String Pronounce;

    public void pronounce() {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice voice;//Creating object of Voice class
        voice = VoiceManager.getInstance().getVoice("kevin");//Getting voice
        if (voice != null) {
            voice.allocate();//Allocating Voice
        }
        try {
            voice.setRate(190);//Setting the rate of the voice
            voice.setPitch(150);//Setting the Pitch of the voice
            voice.setVolume(3);//Setting the volume of the voice
            voice.speak(this.Word);//Calling speak() method
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public String getPronounce() {
        return Pronounce;
    }

    public void setPronounce(String pronounce) {
        Pronounce = pronounce;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public String getData() {
        return Data;
    }

    public void setWord(String word) {
        this.Word = word;
    }

    public String getWord() {
        return this.Word;
    }

    public void setData(String data) {
        this.Data = data;
    }

    public String showWord() {
        return this.Word + "\n" + this.Pronounce + "\n" + this.Data;
    }

    public Word() {};
    public Word(String newWord,String pronoun, String meanWord) {
        this.Word = newWord;
        this.Data = meanWord;
        this.Pronounce = pronoun;
    }

}

