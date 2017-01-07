function Part1(A,B,c)
%Siyi Emily Bao, 10103388

% A: PlanarPoints
% B: SpatialPoints
% c: focus point of A and B

for i=1:5
    pts=[A(:,i)';B(:,i)'];
    plot3(pts(:,1),pts(:,2),pts(:,3))
    hold on;
end
scatter3(c(1),c(2),c(3))