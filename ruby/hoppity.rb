#!/usr/bin/env ruby

def get_input file_name

	file = File.new(file_name, "r")
	line = file.gets
	line = line.strip
	input_number = line.to_i
  input_number
end

puts get_input(ARGV[0])
