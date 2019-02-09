require_relative 'spec_helper'

describe 'Container' do
  describe file('/etc/alpine-release') do
    its(:content) { is_expected.to match(/3.9.0/) }
  end

  describe 'java' do
    describe command('java -version') do
      its(:stderr) { is_expected.to match(/1.8.0_191/) }
    end
  end

  describe file('echo.jar') do
    it { is_expected.to be_file }
  end
end
