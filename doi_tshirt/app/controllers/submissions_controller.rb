class SubmissionsController < ApplicationController
	
	def index
		@submissions = Submission.all
	end
	
	def create
		@submission = Submission.new  params[:submission]

		if @submission.save
			redirect_to @submission,
				:notice => 'new'
		else
			render :action => 'new'
		end
	end

	def show
		@submission = Submission.find(params[:id])
	end

	def new
		@submission = Submission.new
	end

	def destroy
		@submission = Submission.find params[:id]
		@submission.destroy
		redirect_to submissions_url
	end
end
