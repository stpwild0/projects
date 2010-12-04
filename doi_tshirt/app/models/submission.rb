class Submission < ActiveRecord::Base
	validates :email_address, :presence => true
	validates :full_name, :presence => true
	validates :department, :presence => true
end
