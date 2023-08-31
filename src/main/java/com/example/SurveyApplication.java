package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//class imports
import com.example.service.result;
import com.example.service.resultResponse;
import com.example.service.data;

//model and repo imports
import com.example.model.Candidate;
import com.example.model.PersonalInfo;
import com.example.model.SurveySubmission;
import com.example.repository.CandidateRepository;

@CrossOrigin
@RestController
@SpringBootApplication
public class SurveyApplication {

	@Autowired
	private CandidateRepository candidateRepository;

	String[] correctAnswers = {
			"Paris",
			"Mars",
			"Blue whale",
			"Albert Einstein",
			"Au",
			"Carbon dioxide",
			"Skin",
			"William Shakespeare",
			"Mount Everest",
			"Ozone"
	};

	public static void main(String[] args) {
		SpringApplication.run(SurveyApplication.class, args);
	}

	@PostMapping(value = "/submit", consumes = "application/json")
	public int submit(@RequestBody SurveySubmission body) {


		int score = calculateScore(body.getAnswers(), this.correctAnswers);
		result response = new result();
		response.setScore(score);

		if (score >= 6) {
			// Create a new Candidate object and populate it with data

			PersonalInfo personalInfo = body.getPersonalInfo();


			Candidate newCandidate = new Candidate();
			newCandidate.setAge(personalInfo.getAge());
			newCandidate.setEmail(personalInfo.getEmail());
			newCandidate.setFirstName(personalInfo.getFirstName());
			newCandidate.setLastName(personalInfo.getLastName());
			newCandidate.setPhone(personalInfo.getPhone());
			
			candidateRepository.save(newCandidate); // Save the newCandidate to the database
		}
		// Process the survey response (you can save it to a file, etc.)
		// For this example, let's just return a success message.
		return score;
	}

	public int calculateScore(String[] submittedAnswers, String[] correctAnswers) {

		try {

			int totalQuestions = 10;
			int score = 0;

			for (int i = 0; i < totalQuestions; i++) {
				String submittedAnswer = (String) submittedAnswers[i];
				String correctAnswer = (String) correctAnswers[i];

				if (submittedAnswer.equals(correctAnswer)) {
					score++;
				}
			}

			return score;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0; // Default score if there's an issue
	}

}
