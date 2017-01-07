function [approxcomp,approxvec]=pcaapprox(new_data,count,meanVec,eigenvectors)
% [APPROXCOMP,APPROXVEC]=PCAAPPROX(NEW_DATA, APPROXNUM,
%                                       MEANVEC, EVECMAT)
% approximates new data based on a Principal Components Analysis
% (PCA) of initial data. Inputs are:
% NEW_DATA - z1 or z2
% APPROXNUM - a scalar giving the order of the approximation
% MEANVEC - the PCA mean vector (from PCAPRELIM)
% eigenvectors - the eigenvectors of the PCA(from PCAPRELIM)
%
% Return values are:
% APPROXCOMP - the components as a row vector of scalars
% APPROXVEC - the approximation of the new data as a vector
% Set up the return values
for j=1:30
    col=new_data(:,j); % a column vector to be approximated
    diffvec = col - meanVec;
    approxcomp = zeros(count, 1);
    approxvec = meanVec;
    % Loop through the eigenvectors, finding the components
    % and building the approximation
    for i=1:count
        evec = eigenvectors(:,i);
        beta = dot(diffvec, evec);
        approxcomp(i,1) = beta;
        approxvec = approxvec + beta*evec;
    end
end



