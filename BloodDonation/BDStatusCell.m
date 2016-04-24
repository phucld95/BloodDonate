//
//  BDStatusCell.m
//  BloodDonation
//
//  Created by Le Phuc on 4/24/16.
//  Copyright © 2016 Lê Phúc. All rights reserved.
//

#import "BDStatusCell.h"

@implementation BDStatusCell

- (void)awakeFromNib {
    [super awakeFromNib];
    // Initialization code
    _btLike.layer.cornerRadius = 5;
    _btLike.layer.masksToBounds = YES;
    _btShare.layer.cornerRadius = 5;
    _btShare.layer.masksToBounds = YES;
    _btComment.layer.cornerRadius = 5;
    _btComment.layer.masksToBounds = YES;
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}



@end
