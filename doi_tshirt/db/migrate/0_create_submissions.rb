class CreateSubmissions < ActiveRecord::Migration
	def self.up
		create_table :submissions do |t|
			t.string :full_name
			t.string :email_address
			t.string :department
		end
	end

	def self.down
		drop_table :submissions
	end
end
