package com.zjj.design;

import com.zjj.design.util.Topic;
import com.zjj.design.util.TopicRandomUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 主要功能类 获取题目 重写clone方法 避免重复创建对象
 */
@Data
@NoArgsConstructor
public class QuestionBank implements Cloneable{
    private String candidate; // 考生
    private String number;  // 考号

    private ArrayList<ChoiceQuestion> choiceQuestions = new ArrayList<>();
    private ArrayList<AnswerQuestion> answerQuestions = new ArrayList<>();

    public String getCandidate() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public QuestionBank append(ChoiceQuestion choiceQuestion){
        this.choiceQuestions.add(choiceQuestion);
        return this;
    }

    public QuestionBank append(AnswerQuestion answerQuestion){
        this.answerQuestions.add(answerQuestion);
        return this;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        QuestionBank questionBank = (QuestionBank) super.clone();
        // 为什么上面选择List的时候 clone 会报错 因为编译的时候看左边的类型 List是一个接口 没有clone方法 因此报错
        // 编译看左 运行看右边
        questionBank.choiceQuestions = (ArrayList<ChoiceQuestion>)choiceQuestions.clone();
        questionBank.answerQuestions = (ArrayList<AnswerQuestion>) answerQuestions.clone();

        // 题目乱序
        Collections.shuffle(questionBank.answerQuestions);
        Collections.shuffle(questionBank.choiceQuestions);
        for(ChoiceQuestion choiceQuestion : questionBank.choiceQuestions){
            Topic random = TopicRandomUtil.random(choiceQuestion.getOptions(), choiceQuestion.getAnswer());
            choiceQuestion.setAnswer(random.getKey());
            choiceQuestion.setOptions(random.getOptions());
        }
        return questionBank;
    }

    @Override
    public String toString() {

        StringBuilder detail = new StringBuilder("考生：" + candidate + "\r\n" +
                "考号：" + number + "\r\n" +
                "--------------------------------------------\r\n" +
                "一、选择题" + "\r\n\n");

        for (int idx = 0; idx < choiceQuestions.size(); idx++) {
            detail.append("第").append(idx + 1).append("题：").append(choiceQuestions.get(idx).getQuestion()).append("\r\n");
            Map<String, String> option = choiceQuestions.get(idx).getOptions();
            for (String key : option.keySet()) {
                detail.append(key).append("：").append(option.get(key)).append("\r\n");;
            }
            detail.append("答案：").append(choiceQuestions.get(idx).getAnswer()).append("\r\n\n");
        }

        detail.append("二、问答题" + "\r\n\n");

        for (int idx = 0; idx < answerQuestions.size(); idx++) {
            detail.append("第").append(idx + 1).append("题：").append(answerQuestions.get(idx).getQuestion()).append("\r\n");
            detail.append("答案：").append(answerQuestions.get(idx).getAnswer()).append("\r\n\n");
        }

        return detail.toString();
    }
}
